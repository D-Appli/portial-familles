package fr.dappli.portailfamilles.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.dappli.portailfamilles.core.domain.usecase.IsUserAuthenticatedUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberPortailFamillesAppState(
    isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): PortailFamillesAppState {
    return remember(navController) {
        PortailFamillesAppState(isUserAuthenticatedUseCase, coroutineScope, navController)
    }
}

@Stable
class PortailFamillesAppState(
    isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
    coroutineScope: CoroutineScope,
    val navController: NavHostController,
) {

    val isUserAuthenticated: StateFlow<Boolean> = isUserAuthenticatedUseCase()
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )
}
