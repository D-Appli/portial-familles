package fr.dappli.portailfamilles.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import fr.dappli.portailfamilles.core.domain.usecase.IsUserAuthenticatedUseCase
import fr.dappli.portailfamilles.feature.indentity.navigation.navigateToAccount
import fr.dappli.portailfamilles.feature.mycity.navigation.navigateToMyCity
import fr.dappli.portailfamilles.feature.receipts.navigation.navigateToReceipts
import fr.dappli.portailfamilles.feature.reservation.navigation.navigateToReservation
import fr.dappli.portailfamilles.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberPortailFamillesAppState(
    isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): PortailFamillesAppState {
    return remember(navController) {
        PortailFamillesAppState(isUserAuthenticatedUseCase, coroutineScope, navController)
    }
}

@Stable
class PortailFamillesAppState(
    isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
    coroutineScope: CoroutineScope,
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val isUserAuthenticated: StateFlow<Boolean> = isUserAuthenticatedUseCase()
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        with(navController) {
            val topLevelNavOptions = navOptions {
                // close the app when navigate back from the top destination
                popUpTo(0)
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
            when (topLevelDestination) {
                TopLevelDestination.RESERVATION -> navigateToReservation(topLevelNavOptions)
                TopLevelDestination.RECEIPTS -> navigateToReceipts(topLevelNavOptions)
                TopLevelDestination.ACCOUNT -> navigateToAccount(topLevelNavOptions)
                TopLevelDestination.MY_CITY -> navigateToMyCity(topLevelNavOptions)
            }
        }
    }
}
