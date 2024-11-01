package fr.dappli.portailfamilles.feature.indentity.presentation.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.core.presentation.state.StateReducer
import fr.dappli.portailfamilles.feature.indentity.presentation.state.IdentityScreenState

abstract class IdentityScreenReducer(
    initialState: IdentityScreenState,
    dispatcherProvider: DispatcherProvider,
) : StateReducer<IdentityScreenState, IdentityScreenAction>(initialState, dispatcherProvider)
