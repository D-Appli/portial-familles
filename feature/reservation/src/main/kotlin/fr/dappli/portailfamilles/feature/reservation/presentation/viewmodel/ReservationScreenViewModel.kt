package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState
import kotlinx.coroutines.flow.StateFlow

abstract class ReservationScreenViewModel : ViewModel() {
    abstract val stateFlow: StateFlow<ReservationScreenState>
}
