package fr.dappli.portailfamilles.feature.reservation.presentation.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.core.presentation.state.StateReducer
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState

abstract class ReservationScreenReducer(
    initialState: ReservationScreenState,
    dispatcherProvider: DispatcherProvider,
)  : StateReducer<ReservationScreenState, ReservationScreenAction>(initialState, dispatcherProvider)
