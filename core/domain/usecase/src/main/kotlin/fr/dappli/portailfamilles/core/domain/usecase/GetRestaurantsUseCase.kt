package fr.dappli.portailfamilles.core.domain.usecase

import fr.dappli.portailfamilles.core.domain.irepository.MyCityRepository
import fr.dappli.portailfamilles.core.domain.model.mycity.Restaurant
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: MyCityRepository
) {

    suspend operator fun invoke(): List<Restaurant> =
        repository.getRestaurants()
}
