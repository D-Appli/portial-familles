package fr.dappli.portailfamilles.feature.reservation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import fr.dappli.portailfamilles.feature.reservation.ui.ReservationCategoriesScreen
import fr.dappli.portailfamilles.feature.reservation.ui.ReservationSubCategoryScreen
import kotlinx.serialization.Serializable

@Serializable
data object ReservationRoute
@Serializable
data object ReservationCategoriesRoute
@Serializable
data object ReservationSubCategoryRoute

fun NavController.navigateToReservation(navOptions: NavOptions? = null) = navigate(route = ReservationRoute, navOptions)

fun NavGraphBuilder.reservationScreen(
    onSubCategoryClick: (Int) -> Unit
) {
    navigation<ReservationRoute>(startDestination = ReservationCategoriesRoute) {
        composable<ReservationCategoriesRoute> {
            ReservationCategoriesScreen(onSubCategoryClick = onSubCategoryClick)
        }
        composable<ReservationSubCategoryRoute> {
            ReservationSubCategoryScreen()
        }
    }
}
