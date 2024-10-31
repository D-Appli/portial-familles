package fr.dappli.portailfamilles.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import fr.dappli.portailfamilles.feature.indentity.navigation.IdentityRoute
import fr.dappli.portailfamilles.feature.indentity.navigation.identityScreen
import fr.dappli.portailfamilles.feature.reservation.navigation.navigateToReservation
import fr.dappli.portailfamilles.feature.reservation.navigation.reservationScreen
import fr.dappli.portailfamilles.ui.PortailFamillesAppState

@Composable
fun PortailFamillesNavHost(appState: PortailFamillesAppState) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = IdentityRoute,
    ) {
        identityScreen(onSignedIn = navController::navigateToReservation)
        reservationScreen()
    }
}
