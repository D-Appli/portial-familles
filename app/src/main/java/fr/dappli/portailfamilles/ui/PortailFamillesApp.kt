package fr.dappli.portailfamilles.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.dappli.portailfamilles.R
import fr.dappli.portailfamilles.navigation.PortailFamillesNavBar
import fr.dappli.portailfamilles.navigation.PortailFamillesNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortailFamillesApp(
    appState: PortailFamillesAppState
) {
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {

            val stackEntry by appState.navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(null)
            println("andrei stackEntry=$stackEntry")

            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
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
