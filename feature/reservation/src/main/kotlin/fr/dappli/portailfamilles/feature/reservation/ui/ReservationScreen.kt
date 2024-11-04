package fr.dappli.portailfamilles.feature.reservation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModel
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModelImpl

@Composable
internal fun ReservationScreen(
    viewModel: ReservationScreenViewModel = hiltViewModel<ReservationScreenViewModelImpl>()
) {
    var name: String? by remember { mutableStateOf(null) }
    var restaurants: List<Restaurant>? by remember { mutableStateOf(null) }

    Column {
        name?.let { Text("Reservation pour $it") }
        restaurants?.let { Text("Restaurants: $it") }
    }
    LaunchedEffect(Unit) {
        name = viewModel.userDataSource.getUser("28141B") // TODO delete me
        restaurants = viewModel.usecase(offset = 0, limit = 3) // TODO delete me
    }
}
