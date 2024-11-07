package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.domain.usecase.GetReservationCategoriesUseCase
import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.reservation.presentation.model.CallToActions
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState
import fr.dappli.portailfamilles.feature.reservation.presentation.reducer.ReservationScreenAction
import fr.dappli.portailfamilles.feature.reservation.presentation.reducer.ReservationScreenReducer
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationScreenViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val reducer: ReservationScreenReducer,
    getReservationCategoriesUseCase: GetReservationCategoriesUseCase,
) : ReservationScreenViewModel() {

    override val stateFlow: StateFlow<ReservationScreenState> = reducer.stateFlow

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                reducer.update(ReservationScreenAction.SetLoading)
                val categories = getReservationCategoriesUseCase()
                reducer.update(ReservationScreenAction.SetContent(categories, bindCallToAction()))
            } catch (e: Throwable) {
                println("getReservationCategoriesUseCase error $e")
                reducer.update(ReservationScreenAction.SetError)
            }
        }
    }

    private fun bindCallToAction() = CallToActions(
        onSubCategoryClick = ::onSubCategoryClick
    )

    fun onSubCategoryClick(id: Int): Unit {
        // TODO
    }
}
