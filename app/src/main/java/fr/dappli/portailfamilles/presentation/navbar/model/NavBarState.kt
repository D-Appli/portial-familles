package fr.dappli.portailfamilles.presentation.navbar.model

import kotlinx.collections.immutable.ImmutableList
import fr.dappli.portailfamilles.core.presentation.state.State

sealed class NavBarState : State {

    data object None : NavBarState()

    data class Content(
        val items: ImmutableList<String>
    ) : NavBarState()
}
