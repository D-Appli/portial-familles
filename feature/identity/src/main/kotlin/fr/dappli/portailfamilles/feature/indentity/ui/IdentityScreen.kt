package fr.dappli.portailfamilles.feature.indentity.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.dappli.portailfamilles.feature.indentity.presentation.viewmodel.IdentityScreenViewModel
import fr.dappli.portailfamilles.feature.indentity.presentation.viewmodel.IdentityScreenViewModelImpl
import fr.dappli.portailfamilles.feature.indentity.ui.component.SignInForm

@Composable
internal fun IdentityScreen(
    onSignedIn: (String, String) -> Unit,
    viewModel: IdentityScreenViewModel = hiltViewModel<IdentityScreenViewModelImpl>()
) {
    var identityData: Pair<String, String>? by remember { mutableStateOf(null) }
    identityData?.let { data ->
        onSignedIn(data.first, data.second)
    } ?: SignInForm { username, token ->
        identityData = username to token
    }
}
