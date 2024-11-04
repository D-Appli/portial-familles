package fr.dappli.portailfamilles.core.domain.usecase

import fr.dappli.portailfamilles.core.domain.irepository.MyCityRepository
import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: MyCityRepository
) {

    suspend operator fun invoke(offset: Int, limit: Int = DEFAULT_LIMIT): List<Restaurant> {
        when {
            offset < 0 -> throw IllegalArgumentException("offset can not be negative")
            limit > MAX_LIMIT -> throw IllegalArgumentException("limit can not be higher then $MAX_LIMIT")
            offset > MAX_OFFSET -> throw IllegalArgumentException("offset can not be higher then $MAX_OFFSET")
        }
        return repository.getRestaurants(offset, limit)
    }

    private companion object {
        const val DEFAULT_LIMIT = 20
        const val MAX_LIMIT = 100
        const val MAX_OFFSET = 10_000 // Check with your PO
    }
}
