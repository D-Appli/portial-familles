package fr.dappli.portailfamilles.feature.indentity.navigation

import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import fr.dappli.portailfamilles.feature.indentity.ui.IdentityScreen
import kotlinx.serialization.Serializable

@Serializable
data object IdentityRoute

fun NavController.navigateToIdentity(navOptions: NavOptions) = navigate(route = IdentityRoute, navOptions)

fun NavGraphBuilder.identityScreen() {
    composable<IdentityRoute> {
        IdentityScreen()
    }
}
