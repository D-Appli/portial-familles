package fr.dappli.portailfamilles.feature.indentity.presentation.reducer

import fr.dappli.portailfamilles.core.presentation.state.Action

sealed class IdentityScreenAction : Action {
    data object SetDisconnected : IdentityScreenAction()
    data object SetConnected : IdentityScreenAction()
}
