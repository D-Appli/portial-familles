package fr.dappli.portailfamilles.feature.mycity.presentation.model

data class CallToActions(
    val loadMoreItems: (offset: Int) -> Unit
)
