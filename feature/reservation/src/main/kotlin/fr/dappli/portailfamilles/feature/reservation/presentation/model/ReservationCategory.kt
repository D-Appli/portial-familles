package fr.dappli.portailfamilles.feature.reservation.presentation.model

import androidx.compose.runtime.Stable
import fr.dappli.portailfamilles.core.domain.model.form.SubCategory
import kotlinx.collections.immutable.ImmutableList

@Stable
data class ReservationCategory(
    val name: String,
    val imageResId: Int,
    val subcategories: ImmutableList<SubCategory>
)
