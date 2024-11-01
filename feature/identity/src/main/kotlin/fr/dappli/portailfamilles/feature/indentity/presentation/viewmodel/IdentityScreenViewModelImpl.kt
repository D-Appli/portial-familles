package fr.dappli.portailfamilles.feature.indentity.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.data.api.PersistenceKey
import fr.dappli.portailfamilles.core.data.api.PersistentDataSource
import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.indentity.presentation.model.CallToActions
import fr.dappli.portailfamilles.feature.indentity.presentation.model.IdentityScreenState
import fr.dappli.portailfamilles.feature.indentity.presentation.reducer.IdentityScreenAction
import fr.dappli.portailfamilles.feature.indentity.presentation.reducer.IdentityScreenReducer
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdentityScreenViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val reducer: IdentityScreenReducer,
    private val persistentDataSource: PersistentDataSource
) : IdentityScreenViewModel() {

    override val stateFlow: StateFlow<IdentityScreenState> = reducer.stateFlow

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            // TODO verify token and make a call to check if token is not expired
            val userId = persistentDataSource.getString(PersistenceKey.USER_ID.name)
            val token = persistentDataSource.getString(PersistenceKey.TOKEN.name)
            val action = if (userId != null && token != null) {
                IdentityScreenAction.SetSignedIn(userId, token)
            } else {
                IdentityScreenAction.SetSignedOut(bindCallToAction())
            }
            reducer.update(action)
        }
    }

    private fun bindCallToAction() = CallToActions(
        onSignedIn = ::onSignedIn,
    )

    private fun onSignedIn(userId: String, token: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            persistentDataSource.putString(PersistenceKey.USER_ID.name, userId)
            persistentDataSource.putString(PersistenceKey.TOKEN.name, token)
            reducer.update(IdentityScreenAction.SetSignedIn(userId, token))
        }
    }

    private companion object {

    }
}
