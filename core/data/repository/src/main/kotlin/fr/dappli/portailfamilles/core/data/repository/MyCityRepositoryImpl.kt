package fr.dappli.portailfamilles.core.data.repository

import fr.dappli.portailfamilles.core.data.api.MyCityDataSource
import fr.dappli.portailfamilles.core.data.repository.mapper.RestaurantMapper
import fr.dappli.portailfamilles.core.domain.irepository.MyCityRepository
import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant
import javax.inject.Inject

class MyCityRepositoryImpl @Inject constructor(
    private val myCityDataSource: MyCityDataSource,
    private val mapper: RestaurantMapper,
) : MyCityRepository {
    override suspend fun getRestaurants(): List<Restaurant> {
        return mapper.map(myCityDataSource.getRestaurants())
    }
}
