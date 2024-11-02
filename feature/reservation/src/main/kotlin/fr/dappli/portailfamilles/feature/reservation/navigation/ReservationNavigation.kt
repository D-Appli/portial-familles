package fr.dappli.portailfamilles.feature.reservation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import fr.dappli.portailfamilles.feature.reservation.ui.ReservationScreen
import kotlinx.serialization.Serializable

@Serializable
data object ReservationRoute

fun NavController.navigateToReservation(navOptions: NavOptions? = null) = navigate(route = ReservationRoute, navOptions)

fun NavGraphBuilder.reservationScreen() {
    composable<ReservationRoute> {
        ReservationScreen()
    }
}
