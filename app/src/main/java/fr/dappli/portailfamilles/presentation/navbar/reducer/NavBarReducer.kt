package fr.dappli.portailfamilles.presentation.navbar.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.core.presentation.state.StateReducer
import fr.dappli.portailfamilles.presentation.navbar.model.NavBarState

abstract class NavBarReducer(
    initialState: NavBarState,
    dispatcherProvider: DispatcherProvider,
) : StateReducer<NavBarState, NavBarAction>(initialState, dispatcherProvider)
