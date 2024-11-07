package fr.dappli.portailfamilles.feature.reservation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.dappli.portailfamilles.feature.reservation.R
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModel
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModelImpl
import fr.dappli.portailfamilles.feature.reservation.ui.component.ReservationCard
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun ReservationScreen(
    viewModel: ReservationScreenViewModel = hiltViewModel<ReservationScreenViewModelImpl>()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = state) {
            is ReservationScreenState.Content -> ReservationScreenContent(currentState)
            ReservationScreenState.Error -> Text("Error")
            ReservationScreenState.Loading -> CircularProgressIndicator()
            ReservationScreenState.None -> Unit
        }
    }
}

@Composable
private fun BoxScope.ReservationScreenContent(state: ReservationScreenState.Content) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        state.restaurants.first().let { category ->
            ReservationCard(
                category.name,
                R.drawable.school,
                category.items.toImmutableList()
            )
        }

        state.restaurants[1].let { category ->
            ReservationCard(
                category.name,
                R.drawable.winter_vacations,
                category.items.toImmutableList()
            )
        }
    }
}
