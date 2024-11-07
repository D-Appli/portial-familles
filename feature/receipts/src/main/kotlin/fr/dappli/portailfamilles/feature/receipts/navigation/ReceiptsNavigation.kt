package fr.dappli.portailfamilles.feature.receipts.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import fr.dappli.portailfamilles.feature.receipts.ui.ReceiptsScreen
import kotlinx.serialization.Serializable

@Serializable
data object ReceiptsRoute

fun NavController.navigateToReservation(navOptions: NavOptions? = null) = navigate(route = ReceiptsRoute, navOptions)

fun NavGraphBuilder.receiptsScreen() {
    composable<ReceiptsRoute> {
        ReceiptsScreen()
    }
}
