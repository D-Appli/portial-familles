package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import fr.dappli.portailfamilles.core.data.api.FormsDataSource
import fr.dappli.portailfamilles.core.data.api.UserDataSource

abstract class ReservationScreenViewModel : ViewModel() {
    abstract val userDataSource: UserDataSource // TODO delete me
    abstract val formsDataSource: FormsDataSource // TODO delete me
}
