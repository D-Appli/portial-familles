package fr.dappli.portailfamilles.feature.indentity.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import fr.dappli.portailfamilles.feature.indentity.ui.AccountScreen
import kotlinx.serialization.Serializable

@Serializable
data object AccountRoute

fun NavController.navigateToAccount(navOptions: NavOptions? = null) = navigate(route = AccountRoute, navOptions)

fun NavGraphBuilder.accountScreen() {
    composable<AccountRoute> {
        AccountScreen()
    }
}
