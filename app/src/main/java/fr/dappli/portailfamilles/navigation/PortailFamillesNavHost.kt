package fr.dappli.portailfamilles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import fr.dappli.portailfamilles.feature.indentity.navigation.IdentityRoute
import fr.dappli.portailfamilles.feature.indentity.navigation.accountScreen
import fr.dappli.portailfamilles.feature.indentity.navigation.identityScreen
import fr.dappli.portailfamilles.feature.mycity.navigation.myCityScreen
import fr.dappli.portailfamilles.feature.receipts.navigation.receiptsScreen
import fr.dappli.portailfamilles.feature.reservation.navigation.ReservationRoute
import fr.dappli.portailfamilles.feature.reservation.navigation.navigateToReservation
import fr.dappli.portailfamilles.feature.reservation.navigation.navigateToReservationSubCategory
import fr.dappli.portailfamilles.feature.reservation.navigation.reservationScreen
import fr.dappli.portailfamilles.ui.PortailFamillesAppState

@Composable
fun PortailFamillesNavHost(appState: PortailFamillesAppState) {
    val navController = appState.navController
    val isUserAuthenticated by appState.isUserAuthenticated.collectAsStateWithLifecycle()
    val startDestination: Any = if (isUserAuthenticated) ReservationRoute else IdentityRoute
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        identityScreen(onSignedIn = navController::navigateToReservation)
        reservationScreen(onSubCategoryClick = navController::navigateToReservationSubCategory)
        receiptsScreen()
        accountScreen()
        myCityScreen()
    }
}
