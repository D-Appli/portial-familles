package fr.dappli.portailfamilles.feature.reservation.presentation.model

data class CallToActions(
    val onSubCategoryClick: (id: Int) -> Unit
)
