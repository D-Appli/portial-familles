package fr.dappli.portailfamilles.feature.mycity.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = state) {
            is MyCityScreenState.Content -> RestaurantsView(currentState)
            MyCityScreenState.Loading -> CircularProgressIndicator()
            MyCityScreenState.None -> Text("None")
            MyCityScreenState.Error -> Text("Error")
        }
    }
}

@Composable
private fun BoxScope.RestaurantsView(state: MyCityScreenState.Content) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(
            items = state.restaurants,
            key = { _, item ->
                item.title + item.address // TODO add unique item
            }
        ) { index, item ->
            RestaurantCard(item)
            if (index > state.restaurants.size - LOAD_THRESHOLD && state.isLoading.not()) {
                state.loadMoreItems(state.restaurants.size)
            }
        }
    }

    if (state.isLoading) {
        CircularProgressIndicator()
    }
}

private const val LOAD_THRESHOLD = 8
