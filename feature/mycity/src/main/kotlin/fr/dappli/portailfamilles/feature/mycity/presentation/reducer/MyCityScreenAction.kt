package fr.dappli.portailfamilles.feature.mycity.presentation.reducer

import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant
import fr.dappli.portailfamilles.core.presentation.state.Action
import fr.dappli.portailfamilles.feature.mycity.presentation.model.CallToActions

sealed class MyCityScreenAction : Action {

    data object SetLoading : MyCityScreenAction()

    data object SetError : MyCityScreenAction()

    data class SetContent(
        val restaurants: List<Restaurant>,
        val callToActions: CallToActions,
    ) : MyCityScreenAction()

    data class AddContent(
        val restaurants: List<Restaurant>
    ) : MyCityScreenAction()
}
