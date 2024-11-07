package fr.dappli.portailfamilles.core.data.repository

import fr.dappli.portailfamilles.core.data.api.FormsDataSource
import fr.dappli.portailfamilles.core.data.api.PersistenceKey
import fr.dappli.portailfamilles.core.data.api.PersistentDataSource
import fr.dappli.portailfamilles.core.data.model.form.Form
import fr.dappli.portailfamilles.core.data.model.form.Forms
import fr.dappli.portailfamilles.core.data.repository.mapper.CategoryIdMapper
import fr.dappli.portailfamilles.core.data.repository.mapper.FormMapper
import fr.dappli.portailfamilles.core.data.repository.mapper.ThemeMapper
import fr.dappli.portailfamilles.core.domain.model.exception.DomainException
import fr.dappli.portailfamilles.core.domain.model.form.FormId
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import fr.dappli.portailfamilles.core.domain.model.form.Form as DomainForm

class FormRepositoryTest {

    @Test
    fun `verify getForms returns 3 domain forms`() = runTest {
        // given
        val userId = "user123"
        val formMapper = FormMapper(ThemeMapper(CategoryIdMapper()))
        val formsDataSource = mockk<FormsDataSource>()
        coEvery { formsDataSource.getForms(userId) } returns givenForms
        val persistentDataSource = mockk<PersistentDataSource>()
        coEvery { persistentDataSource.getString(PersistenceKey.USER_ID.name) } returns userId

        // when
        val formRepository = FormRepositoryImpl(formsDataSource, persistentDataSource, formMapper)
        val actualForms = formRepository.getForms()

        // then
        coVerify { persistentDataSource.getString(PersistenceKey.USER_ID.name) }
        coVerify { formsDataSource.getForms(userId) }
        assertEquals(expectedForms, actualForms)
    }

    @Test
    fun `verify getForms uses a cache for next calls`() = runTest {
        // given
        val userId = "user123"
        val formMapper = FormMapper(ThemeMapper(CategoryIdMapper()))
        val formsDataSource = mockk<FormsDataSource>()
        coEvery { formsDataSource.getForms(userId) } returns givenForms
        val persistentDataSource = mockk<PersistentDataSource>()
        coEvery { persistentDataSource.getString(PersistenceKey.USER_ID.name) } returns userId

        // when
        val formRepository = FormRepositoryImpl(formsDataSource, persistentDataSource, formMapper)
        val actualForms1 = formRepository.getForms()
        val actualForms2 = formRepository.getForms()
        val actualForms3 = formRepository.getForms()

        // then
        coVerify(exactly = 3) { persistentDataSource.getString(PersistenceKey.USER_ID.name) }
        coVerify(exactly = 1) { formsDataSource.getForms(userId) }
        assertEquals(expectedForms, actualForms1)
        assertEquals(expectedForms, actualForms2)
        assertEquals(expectedForms, actualForms3)
    }

    @Test(expected = DomainException.PageError::class)
    fun `verify getForms throws a domain exception on a wrong code_retour`() = runTest {
        // given
        val userId = "user123"
        val formMapper = FormMapper(ThemeMapper(CategoryIdMapper()))
        val formsDataSource = mockk<FormsDataSource>()
        coEvery { formsDataSource.getForms(userId) } returns givenForms.copy(code_retour = "BAD")
        val persistentDataSource = mockk<PersistentDataSource>()
        coEvery { persistentDataSource.getString(PersistenceKey.USER_ID.name) } returns userId

        // when
        val formRepository = FormRepositoryImpl(formsDataSource, persistentDataSource, formMapper)
        formRepository.getForms()
    }

    private companion object {
        val givenForms = Forms(
            code_retour = "OK",
            listOf(
                Form(
                    id_accueil = 42,
                    libelle = "RESERVATION ET PAIEMENT",
                    order = "1",
                    couleur = "#bc51d2",
                    themes = emptyList()
                ),
                Form(
                    id_accueil = 43,
                    libelle = "MON COMPTE",
                    order = "2",
                    couleur = "#ae60d2",
                    themes = emptyList()
                ),
                Form(
                    id_accueil = 44,
                    libelle = "MES FACTURES",
                    order = "3",
                    couleur = "#ac43e5",
                    themes = emptyList()
                ),
                // this form wont be mapped, wont be supported (contains only html links)
                Form(
                    id_accueil = 86,
                    libelle = "SCOLAIRE",
                    order = "5",
                    couleur = "#c14ce1",
                    themes = emptyList()
                ),
            )
        )

        val expectedForms = listOf(
            DomainForm(
                formId = FormId.RESERVATION,
                label = "RESERVATION ET PAIEMENT",
                categories = emptyList()
            ),
            DomainForm(
                formId = FormId.ACCOUNT,
                label = "MON COMPTE",
                categories = emptyList()
            ),
            DomainForm(
                formId = FormId.RECEIPTS,
                label = "MES FACTURES",
                categories = emptyList()
            )
        )
    }
}
