package fr.dappli.portailfamilles.core.data.remote

import fr.dappli.portailfamilles.core.data.remote.network.NetworkImpl
import fr.dappli.portailfamilles.core.data.remote.utils.mockEngineFromJson
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FormsDataSourceTest {

    @Test
    fun `verify FormsDataSourceImpl`() = runTest {
        // given
        val userId = "user123"
        val network = NetworkImpl(
            persistentDataSource = mockk(relaxed = true),
            mockEngine = mockEngineFromJson("formulaires.json")
        )
        val sut = FormsDataSourceImpl(network.client)

        // when
        val forms = sut.getForms(userId)

        // then
        assertEquals(forms.code_retour, "OK")
        val actualForms = forms.accueils?.mapNotNull { it?.libelle }
        assertEquals(expectedFormLabels, actualForms)
    }

    private companion object {
        val expectedFormLabels = listOf(
            "RESERVATION ET PAIEMENT",
            "MON COMPTE",
            "MES FACTURES ",
            "SCOLAIRE"
        )
    }
}
