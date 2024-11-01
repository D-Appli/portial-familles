package fr.dappli.portailfamilles.feature.reservation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import fr.dappli.portailfamilles.feature.reservation.ui.ReservationScreen
import kotlinx.serialization.Serializable

@Serializable
data class ReservationRoute(
    val userId: String
)

fun NavController.navigateToReservation(
    username: String, navOptions: NavOptions? = null
) = navigate(route = ReservationRoute(username), navOptions)

fun NavGraphBuilder.reservationScreen() {
    composable<ReservationRoute> {
        val route = it.toRoute<ReservationRoute>()
        ReservationScreen(route.userId)
    }
}
