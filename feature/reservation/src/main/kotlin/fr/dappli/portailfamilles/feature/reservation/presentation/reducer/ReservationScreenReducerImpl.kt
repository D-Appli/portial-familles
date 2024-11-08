package fr.dappli.portailfamilles.feature.reservation.presentation.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.reservation.presentation.mapper.ReservationScreenMapper
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState
import fr.dappli.portailfamilles.feature.reservation.presentation.reducer.ReservationScreenAction.SetContent
import fr.dappli.portailfamilles.feature.reservation.presentation.reducer.ReservationScreenAction.SetError
import fr.dappli.portailfamilles.feature.reservation.presentation.reducer.ReservationScreenAction.SetLoading
import javax.inject.Inject

class ReservationScreenReducerImpl @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val mapper: ReservationScreenMapper,
) : ReservationScreenReducer(
    initialState = ReservationScreenState.None,
    dispatcherProvider = dispatcherProvider
) {

    override fun reduce(
        action: ReservationScreenAction,
        currentState: ReservationScreenState
    ): ReservationScreenState {
        return when (action) {
            is SetLoading -> onSetLoading(action, currentState)
            is SetContent -> onSetContent(action, currentState)
            is SetError -> onSetError(action, currentState)
        }
    }

    private fun onSetLoading(action: SetLoading, currentState: ReservationScreenState): ReservationScreenState {
        return ReservationScreenState.Loading
    }

    private fun onSetContent(action: SetContent, currentState: ReservationScreenState): ReservationScreenState {
        return mapper.map(
            ReservationScreenMapper.Params(action.categories, action.callToActions)
        )
    }

    private fun onSetError(action: SetError, currentState: ReservationScreenState): ReservationScreenState {
        return ReservationScreenState.Error
    }
}
