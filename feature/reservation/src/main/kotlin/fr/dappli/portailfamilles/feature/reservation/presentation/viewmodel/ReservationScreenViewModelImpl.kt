package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.domain.usecase.GetReservationCategoriesUseCase
import javax.inject.Inject

@HiltViewModel
class ReservationScreenViewModelImpl @Inject constructor(
    _getReservationCategoriesUseCase: GetReservationCategoriesUseCase,
) : ReservationScreenViewModel() {

    override val usecase: GetReservationCategoriesUseCase = _getReservationCategoriesUseCase
}
