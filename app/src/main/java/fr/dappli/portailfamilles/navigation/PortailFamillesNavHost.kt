package fr.dappli.portailfamilles.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import fr.dappli.portailfamilles.feature.indentity.navigation.IdentityRoute
import fr.dappli.portailfamilles.feature.indentity.navigation.accountScreen
import fr.dappli.portailfamilles.feature.indentity.navigation.identityScreen
import fr.dappli.portailfamilles.feature.mycity.navigation.myCityScreen
import fr.dappli.portailfamilles.feature.receipts.navigation.receiptsScreen
import fr.dappli.portailfamilles.feature.reservation.navigation.navigateToReservationSubCategory
import fr.dappli.portailfamilles.feature.reservation.navigation.reservationScreen
import fr.dappli.portailfamilles.ui.PortailFamillesAppState

@Composable
fun PortailFamillesNavHost(appState: PortailFamillesAppState) {
    val navController = appState.navController
    val startDestination: Any = IdentityRoute
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        identityScreen(onSignedIn = {
            appState.navigateToTopLevelDestination(TopLevelDestination.RESERVATION)
        })
        reservationScreen(onSubCategoryClick = navController::navigateToReservationSubCategory)
        receiptsScreen()
        accountScreen()
        myCityScreen()
    }
}
