package fr.dappli.portailfamilles.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberPortailFamillesAppState(
    navController: NavHostController = rememberNavController(),
): PortailFamillesAppState {
    return remember(navController) {
        PortailFamillesAppState(navController)
    }
}

@Stable
class PortailFamillesAppState(
    val navController: NavHostController,
)
