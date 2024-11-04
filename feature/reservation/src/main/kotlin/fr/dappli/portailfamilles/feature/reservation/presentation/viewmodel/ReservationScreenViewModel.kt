package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.domain.usecase.GetRestaurantsUseCase

abstract class ReservationScreenViewModel : ViewModel() {
    abstract val userDataSource: UserDataSource // TODO delete me
    abstract val usecase: GetRestaurantsUseCase // TODO delete me
}
