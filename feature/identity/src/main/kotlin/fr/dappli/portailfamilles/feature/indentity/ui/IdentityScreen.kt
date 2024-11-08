package fr.dappli.portailfamilles.feature.indentity.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
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
    // Convert the system status bar height to Dp
    val statusBarHeight = with(LocalDensity.current) {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = statusBarHeight),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = state) {
            IdentityScreenState.None -> CircularProgressIndicator()
            is SignedIn -> {
                CircularProgressIndicator()
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

}
