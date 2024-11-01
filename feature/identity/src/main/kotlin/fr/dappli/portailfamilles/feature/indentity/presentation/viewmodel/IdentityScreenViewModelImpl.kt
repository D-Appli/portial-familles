package fr.dappli.portailfamilles.feature.indentity.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.indentity.presentation.model.CallToActions
import fr.dappli.portailfamilles.feature.indentity.presentation.model.IdentityScreenState
import fr.dappli.portailfamilles.feature.indentity.presentation.reducer.IdentityScreenAction
import fr.dappli.portailfamilles.feature.indentity.presentation.reducer.IdentityScreenReducerImpl
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdentityScreenViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val reducer: IdentityScreenReducerImpl,
) : IdentityScreenViewModel() {

    override val stateFlow: StateFlow<IdentityScreenState> = reducer.stateFlow

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            // TODO verify token and make a call to check if token is not expired
            reducer.update(IdentityScreenAction.SetSignedOut(bindCallToAction()))
        }
    }

    private fun bindCallToAction() = CallToActions(
        onSignedIn = ::onSignedIn,
    )

    private fun onSignedIn(userId: String, token: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            reducer.update(IdentityScreenAction.SetSignedIn(userId, token))
        }
    }
}
