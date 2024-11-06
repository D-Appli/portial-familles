package fr.dappli.portailfamilles.feature.reservation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.feature.reservation.R
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModel
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModelImpl
import fr.dappli.portailfamilles.feature.reservation.ui.component.ReservationCard
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun ReservationScreen(
    viewModel: ReservationScreenViewModel = hiltViewModel<ReservationScreenViewModelImpl>()
) {
    val scrollState = rememberScrollState()
    var reservationCategories: List<Category>? by remember { mutableStateOf(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        reservationCategories?.first()?.let { category ->
            ReservationCard(
                category.name,
                R.drawable.school,
                category.items.toImmutableList()
            )
        }

        reservationCategories?.get(1)?.let { category ->
            ReservationCard(
                category.name,
                R.drawable.winter_vacations,
                category.items.toImmutableList()
            )
        }
    }
    LaunchedEffect(Unit) {
        reservationCategories = viewModel.usecase()
    }
}
