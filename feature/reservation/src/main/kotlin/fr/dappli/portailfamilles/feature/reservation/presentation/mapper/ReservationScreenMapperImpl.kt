package fr.dappli.portailfamilles.feature.reservation.presentation.mapper

import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationCategory
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState.Content
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class ReservationScreenMapperImpl @Inject constructor(
    private val resourceMapper: CategoryImageResourceMapper,
) : ReservationScreenMapper {

    override fun map(param: ReservationScreenMapper.Params): ReservationScreenState {
        val reservations = param.categories.map {
            ReservationCategory(
                it.name,
                resourceMapper.map(it.categoryId),
                it.items.toImmutableList()
            )
        }.toImmutableList()
        return Content(
            reservations,
            param.callToActions.onSubCategoryClick,
        )
    }
}
