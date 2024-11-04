package fr.dappli.portailfamilles.feature.mycity.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import fr.dappli.portailfamilles.feature.mycity.ui.MyCityScreen
import kotlinx.serialization.Serializable

@Serializable
data object MyCityRoute

fun NavController.navigateToMyCity(navOptions: NavOptions? = null) = navigate(route = MyCityRoute, navOptions)

fun NavGraphBuilder.myCityScreen() {
    composable<MyCityRoute> {
        MyCityScreen()
    }
}
