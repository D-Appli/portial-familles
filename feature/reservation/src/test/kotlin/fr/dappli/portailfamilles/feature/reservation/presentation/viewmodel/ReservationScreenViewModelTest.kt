package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import app.cash.turbine.test
import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.core.domain.model.form.CategoryId
import fr.dappli.portailfamilles.core.domain.usecase.GetReservationCategoriesUseCase
import fr.dappli.portailfamilles.feature.reservation.presentation.dispatcher.TestDispatcherProvider
import fr.dappli.portailfamilles.feature.reservation.presentation.mapper.CategoryImageResourceMapperImpl
import fr.dappli.portailfamilles.feature.reservation.presentation.mapper.ReservationScreenMapperImpl
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState
import fr.dappli.portailfamilles.feature.reservation.presentation.reducer.ReservationScreenReducerImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ReservationScreenViewModelTest {

    // TODO could be injected
    private val reducer = ReservationScreenReducerImpl(
        dispatcherProvider = TestDispatcherProvider,
        mapper = ReservationScreenMapperImpl(
            CategoryImageResourceMapperImpl()
        )
    )

    private fun getSut(useCase: GetReservationCategoriesUseCase): ReservationScreenViewModel {
        return ReservationScreenViewModelImpl(
            TestDispatcherProvider,
            reducer,
            useCase
        )
    }

    @Test
    fun `verify vm emits content state at initialisation`() = runTest {
        // given
        val useCase = mockk<GetReservationCategoriesUseCase>()
        coEvery { useCase() } returns categories

        // when
        val sut = getSut(useCase)

        // then
        coEvery { useCase() }
        sut.stateFlow.test {
            // verify the state is Content
            val state = awaitItem()
            assertTrue(state is ReservationScreenState.Content)

            // verify category names
            val actualState = (state as ReservationScreenState.Content)
            val actualNames = actualState.reservations.map {
                it.name
            }
            val expectedNames = categories.map { it.name }
            assertEquals(expectedNames, actualNames)

            expectNoEvents()
        }
    }

    @Test
    fun `verify vm emits error state on exception`() = runTest {
        // given
        val useCase = mockk<GetReservationCategoriesUseCase>()
        coEvery { useCase() } throws IllegalArgumentException("my exception")

        // when
        val sut = getSut(useCase)

        // then
        coEvery { useCase() }
        sut.stateFlow.test {
            // verify the state is Error
            val state = awaitItem()
            assertTrue(state is ReservationScreenState.Error)
            expectNoEvents()
        }
    }

    private companion object {
        val categories = listOf(
            Category(
                categoryId = CategoryId.PERI_SCOLAIRE,
                name = "Peri-Scolaire",
                items = emptyList()
            ),
            Category(
                categoryId = CategoryId.EXTRA_SCOLAIRE,
                name = "Extra-scolaire",
                items = emptyList()
            ),
            Category(
                categoryId = CategoryId.ARTELIER_INISPORT,
                name = "Artelier - inisport",
                items = emptyList()
            ),
        )
    }
}
