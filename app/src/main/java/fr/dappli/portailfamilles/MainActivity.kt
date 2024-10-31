package fr.dappli.portailfamilles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.data.remote.UserDataSourceImpl
import fr.dappli.portailfamilles.core.data.remote.network.NetworkImpl
import fr.dappli.portailfamilles.ui.component.SignInForm
import fr.dappli.portailfamilles.ui.theme.PortailFamillesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortailFamillesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        var connectionData: Pair<String, String>? by remember { mutableStateOf(null) }
                        connectionData?.let { data ->
                            var name: String? by remember { mutableStateOf(null) }
                            name?.let {
                                Greeting(
                                    name = it,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                            LaunchedEffect(Unit) {
                                val network = NetworkImpl()
                                val dataSource: UserDataSource = UserDataSourceImpl(network.client)
                                name = dataSource.getUser(data.first, data.second)
                            }
                        } ?: SignInForm { username, token ->
                            connectionData = username to token
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
