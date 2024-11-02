package fr.dappli.portailfamilles.feature.indentity.presentation.reducer

import fr.dappli.portailfamilles.core.presentation.state.Action
import fr.dappli.portailfamilles.feature.indentity.presentation.model.CallToActions

sealed class IdentityScreenAction : Action {

    data class SetSignedOut(
        val callToActions: CallToActions,
    ) : IdentityScreenAction()

    data object SetSignedIn : IdentityScreenAction()
}
