package fr.dappli.portailfamilles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import fr.dappli.portailfamilles.ui.PortailFamillesApp
import fr.dappli.portailfamilles.ui.rememberPortailFamillesAppState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberPortailFamillesAppState()
            PortailFamillesApp(appState)
        }
    }
}
