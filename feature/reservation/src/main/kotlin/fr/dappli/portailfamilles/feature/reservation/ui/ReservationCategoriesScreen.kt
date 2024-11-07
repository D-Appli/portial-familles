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
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModel
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModelImpl
import fr.dappli.portailfamilles.feature.reservation.ui.component.ReservationCard

@Composable
internal fun ReservationCategoriesScreen(
    viewModel: ReservationScreenViewModel = hiltViewModel<ReservationScreenViewModelImpl>(),
    onSubCategoryClick: (Int) -> Unit
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = state) {
            is ReservationScreenState.Content -> ReservationScreenContent(
                currentState,
                onSubCategoryClick
            )
            ReservationScreenState.Error -> Text("Error")
            ReservationScreenState.Loading -> CircularProgressIndicator()
            ReservationScreenState.None -> Unit
        }
    }
}

@Composable
private fun BoxScope.ReservationScreenContent(
    state: ReservationScreenState.Content,
    onSubCategoryClick: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    // We have a small amount of reservation items, so column with scroll state is fine
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        state.reservations.forEach {
            ReservationCard(
                it.name,
                it.imageResId,
                it.subcategories,
                onSubCategoryClick,
            )
        }
    }
}
