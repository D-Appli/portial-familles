package fr.dappli.portailfamilles.feature.indentity.presentation.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.indentity.presentation.state.IdentityScreenState

class IdentityScreenReducerImpl(
    dispatcherProvider: DispatcherProvider,
) : IdentityScreenReducer(
    initialState = IdentityScreenState.None,
    dispatcherProvider = dispatcherProvider
) {

    override fun reduce(
        action: IdentityScreenAction,
        currentState: IdentityScreenState
    ): IdentityScreenState {
        TODO("Not yet implemented")
    }

}
