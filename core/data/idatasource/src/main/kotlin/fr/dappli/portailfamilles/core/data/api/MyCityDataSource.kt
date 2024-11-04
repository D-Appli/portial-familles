package fr.dappli.portailfamilles.core.data.api

import fr.dappli.portailfamilles.core.data.model.mycity.Restaurants

interface MyCityDataSource {
    suspend fun getRestaurants(offset: Int, limit: Int): Restaurants
}
