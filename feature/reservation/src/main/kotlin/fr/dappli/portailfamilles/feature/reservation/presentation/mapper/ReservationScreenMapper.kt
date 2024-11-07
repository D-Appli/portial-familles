package fr.dappli.portailfamilles.feature.reservation.presentation.mapper

import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.core.kotlin.mapper.Mapper
import fr.dappli.portailfamilles.feature.reservation.presentation.model.CallToActions
import fr.dappli.portailfamilles.feature.reservation.presentation.model.ReservationScreenState

interface ReservationScreenMapper : Mapper<ReservationScreenMapper.Params, ReservationScreenState> {

    data class Params(
        val categories: List<Category>,
        val callToActions: CallToActions,
    )
}
