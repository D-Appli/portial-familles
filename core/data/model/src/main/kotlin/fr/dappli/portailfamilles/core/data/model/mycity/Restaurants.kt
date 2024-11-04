package fr.dappli.portailfamilles.core.data.model.mycity

import kotlinx.serialization.Serializable

@Serializable
data class Restaurants(
    val restaurants: List<Restaurant?>?
)
