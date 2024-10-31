package fr.dappli.portailfamilles.feature.reservation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.data.remote.UserDataSourceImpl
import fr.dappli.portailfamilles.core.data.remote.network.NetworkImpl

@Composable
internal fun ReservationScreen(
    username: String,
    token: String
) {
    var name: String? by remember { mutableStateOf(null) }
    name?.let {
        Text("Reservation pour $it")
    }
    LaunchedEffect(Unit) {
        val network = NetworkImpl()
        val dataSource: UserDataSource = UserDataSourceImpl(network.client)
        name = dataSource.getUser(username, token)
    }
}
