package fr.dappli.portailfamilles.core.data.repository.mapper

import fr.dappli.portailfamilles.core.data.model.mycity.Restaurants
import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant
import fr.dappli.portailfamilles.core.kotlin.mapper.Mapper
import javax.inject.Inject

class RestaurantMapper @Inject constructor() : Mapper<Restaurants, List<Restaurant>> {

    override fun map(param: Restaurants): List<Restaurant> {
        return param.restaurants?.mapNotNull {
            val title = it?.title
            val address = it?.address
            if (title != null && address != null) {
                Restaurant(title, address, it.description, it.imageUrl)
            } else {
                null
            }
        } ?: emptyList()
    }
}
