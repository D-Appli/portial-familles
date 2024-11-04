package fr.dappli.portailfamilles.core.domain.irepository

import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant

interface MyCityRepository {
    suspend fun getRestaurants(offset: Int, limit: Int): List<Restaurant>
}
