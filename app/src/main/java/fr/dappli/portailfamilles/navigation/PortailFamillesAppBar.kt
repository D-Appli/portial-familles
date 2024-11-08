package fr.dappli.portailfamilles.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.dappli.portailfamilles.R
import fr.dappli.portailfamilles.core.ui.navigation.NestedRoute
import fr.dappli.portailfamilles.feature.indentity.navigation.IdentityRoute
import fr.dappli.portailfamilles.ui.PortailFamillesAppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortailFamillesAppBar(
    appState: PortailFamillesAppState,
) {
    val stackEntry by appState.navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(
        null
    )
    val route = stackEntry?.destination?.route
    val topLevelDestination = TopLevelDestination.entries.find { it.route.qualifiedName == route }

    if (route != IdentityRoute::class.qualifiedName) {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                val title = if (topLevelDestination != null) {
                    val res = topLevelDestination.screenNameResId
                    stringResource(res)
                } else {
                    stackEntry?.arguments?.getString(NestedRoute::name.name)
                        ?: stringResource(R.string.app_name)
                }
                Text(title)
            },
            navigationIcon = {
                if (topLevelDestination == null) {
                    IconButton(onClick = {
                        appState.navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Back"
                        )
                    }
                }
            }
        )
    }
}
