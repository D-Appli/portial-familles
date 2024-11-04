package fr.dappli.portailfamilles.feature.mycity.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.dappli.portailfamilles.feature.mycity.presentation.model.MyCityScreenState
import fr.dappli.portailfamilles.feature.mycity.presentation.viewmodel.MyCityScreenViewModel
import fr.dappli.portailfamilles.feature.mycity.presentation.viewmodel.MyCityScreenViewModelImpl

@Composable
fun MyCityScreen(
    viewModel: MyCityScreenViewModel = hiltViewModel<MyCityScreenViewModelImpl>()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    when (val currentState = state) {
        is MyCityScreenState.Content -> RestaurantsView(currentState)
        MyCityScreenState.Loading -> Text("Loading")
        MyCityScreenState.None -> Unit
        MyCityScreenState.Error -> Text("Error")
    }
}

@Composable
private fun RestaurantsView(state: MyCityScreenState.Content) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(state.restaurants) { index, item ->
            Column(Modifier.padding(vertical = 4.dp)) {
                Text(item.title)
                Text(item.address)
            }
        }
    }
}
