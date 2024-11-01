package fr.dappli.portailfamilles.feature.indentity.presentation.model

import fr.dappli.portailfamilles.core.presentation.state.State

sealed class IdentityScreenState : State {

    data object None : IdentityScreenState()

    data class SignedOut(
        val onSignedIn: (userId: String, token: String) -> Unit
    ) : IdentityScreenState()

    data class SignedIn(
        val userId: String,
        val token: String,
    ) : IdentityScreenState()
}
