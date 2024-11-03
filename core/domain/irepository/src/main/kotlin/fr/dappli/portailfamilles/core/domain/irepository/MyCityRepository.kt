package fr.dappli.portailfamilles.core.domain.irepository

import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant

interface MyCityRepository {
    suspend fun getRestaurants(): List<Restaurant>
}
