package fr.dappli.portailfamilles.feature.reservation.presentation.reducer

import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.core.presentation.state.Action
import fr.dappli.portailfamilles.feature.reservation.presentation.model.CallToActions

sealed class ReservationScreenAction : Action {

    data object SetLoading : ReservationScreenAction()

    data object SetError : ReservationScreenAction()

    data class SetContent(
        val categories: List<Category>,
        val callToActions: CallToActions,
    ) : ReservationScreenAction()
}
