package fr.dappli.portailfamilles.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import fr.dappli.portailfamilles.presentation.navbar.model.NavBarState
import fr.dappli.portailfamilles.presentation.navbar.viewmodel.NavBarViewModel
import fr.dappli.portailfamilles.presentation.navbar.viewmodel.NavBarViewModelImpl
import fr.dappli.portailfamilles.ui.PortailFamillesAppState
import kotlin.reflect.KClass

@Composable
fun PortailFamillesNavBar(
    appState: PortailFamillesAppState,
    viewModel: NavBarViewModel = hiltViewModel<NavBarViewModelImpl>()
) {
    val currentDestination = appState.currentDestination
    val topLevelDestination = TopLevelDestination.entries.find { currentDestination?.hasRoute(it.route) ?: false }
    AnimatedVisibility(
        visible = topLevelDestination != null,
        enter = fadeIn(tween(1000)) + expandVertically(tween(durationMillis = 1000)),
        exit = fadeOut(tween(1000)) + shrinkVertically(tween(durationMillis = 1000)),
    ) {
        val state by viewModel.stateFlow.collectAsStateWithLifecycle()
        when (val currentState = state) {
            NavBarState.None -> Unit
            is NavBarState.Content -> {

                NavigationBar {
                    currentState.items.forEach { item ->
                        val selected = currentDestination.isRouteInHierarchy(item.route)
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painterResource(if (selected) item.filledImageResId else item.outlinedImageResId),
                                    contentDescription = item.name
                                )
                            },
                            label = { Text(stringResource(item.screenNameResId)) },
                            selected = selected,
                            onClick = {
                                appState.navigateToTopLevelDestination(item)
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false
