package fr.dappli.portailfamilles.feature.indentity.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.dappli.portailfamilles.feature.indentity.presentation.model.IdentityScreenState
import fr.dappli.portailfamilles.feature.indentity.presentation.model.IdentityScreenState.SignedIn
import fr.dappli.portailfamilles.feature.indentity.presentation.model.IdentityScreenState.SignedOut
import fr.dappli.portailfamilles.feature.indentity.presentation.viewmodel.IdentityScreenViewModel
import fr.dappli.portailfamilles.feature.indentity.presentation.viewmodel.IdentityScreenViewModelImpl
import fr.dappli.portailfamilles.feature.indentity.ui.component.SignInForm

@Composable
internal fun IdentityScreen(
    onSignedIn: () -> Unit,
    viewModel: IdentityScreenViewModel = hiltViewModel<IdentityScreenViewModelImpl>()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    when (val currentState = state) {
        IdentityScreenState.None -> Unit

        is SignedIn -> {
            // TODO add animation
            Text("Connected")
            onSignedIn()
        }
        is SignedOut -> {
            SignInForm { userId, token ->
                //identityData = username to token
                currentState.onSignedIn(userId, token)
            }
        }
    }
}
