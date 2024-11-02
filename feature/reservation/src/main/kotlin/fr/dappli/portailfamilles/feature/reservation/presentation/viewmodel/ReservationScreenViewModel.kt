package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import fr.dappli.portailfamilles.core.data.api.UserDataSource

abstract class ReservationScreenViewModel : ViewModel() {
    abstract val dataSource: UserDataSource // TODO delete me
}
