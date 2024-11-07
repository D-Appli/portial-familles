package fr.dappli.portailfamilles.feature.reservation.presentation.model

import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant
import fr.dappli.portailfamilles.core.presentation.state.State
import kotlinx.collections.immutable.ImmutableList

sealed class ReservationScreenState : State {

    data object None : ReservationScreenState()

    data object Loading : ReservationScreenState()

    data object Error : ReservationScreenState()

    data class Content(
        val restaurants: ImmutableList<Category>,
        val onSubCategoryClick: (id: Int) -> Unit
    ) : ReservationScreenState()
}
