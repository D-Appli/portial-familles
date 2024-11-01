package fr.dappli.portailfamilles.feature.indentity.presentation.state

import fr.dappli.portailfamilles.core.presentation.state.State

sealed class IdentityScreenState : State {
    data object None : IdentityScreenState()
    // TODO we can add also Loading and Error states to handle web page errors of webview
}
