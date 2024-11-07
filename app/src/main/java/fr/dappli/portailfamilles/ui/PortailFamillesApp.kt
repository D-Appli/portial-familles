package fr.dappli.portailfamilles.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.dappli.portailfamilles.navigation.PortailFamillesAppBar
import fr.dappli.portailfamilles.navigation.PortailFamillesNavBar
import fr.dappli.portailfamilles.navigation.PortailFamillesNavHost

@Composable
fun PortailFamillesApp(
    appState: PortailFamillesAppState
) {
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            PortailFamillesAppBar(appState)
        },
        bottomBar = {
            val isUserAuthenticated by appState.isUserAuthenticated.collectAsStateWithLifecycle()
            if (isUserAuthenticated) {
                PortailFamillesNavBar(appState)
            }
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .background(MaterialTheme.colorScheme.secondaryContainer) // TODO add gradient background for the app
        ) {
            PortailFamillesNavHost(appState)
        }
    }
}
