package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.domain.usecase.GetRestaurantsUseCase
import javax.inject.Inject

@HiltViewModel
class ReservationScreenViewModelImpl @Inject constructor(
    _userDataSourceImpl: UserDataSource,
    _usecase: GetRestaurantsUseCase,
) : ReservationScreenViewModel() {

    override val userDataSource: UserDataSource = _userDataSourceImpl
    override val usecase: GetRestaurantsUseCase = _usecase
}
