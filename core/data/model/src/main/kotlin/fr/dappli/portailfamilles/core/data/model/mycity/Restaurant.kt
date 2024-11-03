package fr.dappli.portailfamilles.core.data.model.mycity

import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    val title: String?,
    val address: String?,
    val description: String?,
    val imageUrl: String?
)
