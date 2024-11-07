package fr.dappli.portailfamilles.presentation.navbar.model

import kotlinx.collections.immutable.ImmutableList
import fr.dappli.portailfamilles.core.presentation.state.State
import fr.dappli.portailfamilles.navigation.TopLevelDestination

sealed class NavBarState : State {

    data object None : NavBarState()

    data class Content(
        val items: ImmutableList<TopLevelDestination>
    ) : NavBarState()
}
