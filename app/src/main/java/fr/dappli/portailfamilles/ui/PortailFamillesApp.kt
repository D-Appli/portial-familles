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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            PortailFamillesAppBar(appState)
        },
        bottomBar = {
            PortailFamillesNavBar(appState)
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            PortailFamillesNavHost(appState)
        }
    }
}
