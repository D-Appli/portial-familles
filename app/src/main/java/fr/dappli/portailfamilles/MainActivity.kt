package fr.dappli.portailfamilles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import fr.dappli.portailfamilles.core.data.api.LoginDataSource
import fr.dappli.portailfamilles.core.data.remote.LoginDataSourceImpl
import fr.dappli.portailfamilles.core.data.remote.network.NetworkImpl
import fr.dappli.portailfamilles.ui.theme.PortailFamillesTheme
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortailFamillesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        val scope = rememberCoroutineScope()
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Button(onClick = {
                            scope.launch {
                                val network = NetworkImpl()
                                val dataSource: LoginDataSource = LoginDataSourceImpl(network.client)
                                println("${dataSource.login("28141B", "F@milleMand20!6")}")
                            }
                        }) {
                            Text("Login")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PortailFamillesTheme {
        Greeting("Android")
    }
}
