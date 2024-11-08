package fr.dappli.portailfamilles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import fr.dappli.portailfamilles.core.domain.usecase.IsUserAuthenticatedUseCase
import fr.dappli.portailfamilles.ui.PortailFamillesApp
import fr.dappli.portailfamilles.ui.rememberPortailFamillesAppState
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberPortailFamillesAppState(isUserAuthenticatedUseCase)

            println("andrei navController: ${appState.navController}, destination: ${appState.currentDestination}")
//            println("andrei previousBackStackEntry=${appState.navController.previousBackStackEntry}")
            PortailFamillesApp(appState)
        }
    }
}
