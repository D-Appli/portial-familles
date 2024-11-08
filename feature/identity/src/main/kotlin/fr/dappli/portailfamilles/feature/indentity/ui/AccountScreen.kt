package fr.dappli.portailfamilles.feature.indentity.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors.fromApplication
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.core.domain.irepository.AuthenticationRepository
import fr.dappli.portailfamilles.core.ui.component.WorkInProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
internal fun AccountScreen() {
    Column {
        // This code should be deleted and a viewmodel should be used like for reservation feature
        var user: String by remember { mutableStateOf("Unauthorized") }
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val repository = remember {
            fromApplication(context, JustToTestEntryPoint::class.java).repository()
        }
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = user,
            style = MaterialTheme.typography.bodyLarge
        )
        WorkInProgress()
        LaunchedEffect(Unit) {
            scope.launch(Dispatchers.IO) {
                try {
                    repository.getUserId()?.let { userId ->
                        user = repository.getUser(userId)
                    }
                } catch (e: Throwable) {
                    println("it's time to sign-out $e")
                    user = "Unauthorized"
                }
            }
        }
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface JustToTestEntryPoint {
    fun repository(): AuthenticationRepository
}
