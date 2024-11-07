package fr.dappli.portailfamilles.feature.indentity.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import fr.dappli.portailfamilles.feature.indentity.ui.IdentityScreen
import kotlinx.serialization.Serializable

@Serializable
data object IdentityRoute

fun NavController.navigateToIdentity(navOptions: NavOptions? = null) = navigate(route = IdentityRoute, navOptions)

fun NavGraphBuilder.identityScreen(
    onSignedIn: () -> Unit
) {
    composable<IdentityRoute> {
        IdentityScreen(onSignedIn)
    }
}
