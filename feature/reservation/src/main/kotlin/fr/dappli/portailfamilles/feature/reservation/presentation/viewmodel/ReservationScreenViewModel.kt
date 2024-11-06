package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import fr.dappli.portailfamilles.core.domain.usecase.GetReservationCategoriesUseCase

abstract class ReservationScreenViewModel : ViewModel() {
    abstract val usecase: GetReservationCategoriesUseCase // TODO delete me
}
