package fr.dappli.portailfamilles.feature.indentity.presentation.reducer

import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.indentity.presentation.model.IdentityScreenState
import javax.inject.Inject

class IdentityScreenReducerImpl @Inject constructor(
    dispatcherProvider: DispatcherProvider,
) : IdentityScreenReducer(
    initialState = IdentityScreenState.None,
    dispatcherProvider = dispatcherProvider
) {

    override fun reduce(
        action: IdentityScreenAction,
        currentState: IdentityScreenState
    ): IdentityScreenState {
        return when (action) {
            is IdentityScreenAction.SetSignedIn -> IdentityScreenState.SignedIn(action.userId)
            is IdentityScreenAction.SetSignedOut -> IdentityScreenState.SignedOut(
                action.callToActions.onSignedIn
            )
        }
    }

}
