package fr.dappli.portailfamilles.feature.reservation.presentation.mapper

import fr.dappli.portailfamilles.core.domain.model.form.CategoryId
import fr.dappli.portailfamilles.feature.reservation.R
import javax.inject.Inject

// TODO we can replace static images by remote ones provided by our own server in the future
class CategoryImageResourceMapperImpl @Inject constructor() : CategoryImageResourceMapper {
    override fun map(param: CategoryId): Int {
        return when (param) {
            CategoryId.PERI_SCOLAIRE -> R.drawable.school
            CategoryId.EXTRA_SCOLAIRE -> R.drawable.winter_vacations
            CategoryId.ARTELIER_INISPORT -> R.drawable.artelier_inisport
            CategoryId.MES_LIENS_UTILES -> R.drawable.unknown
            CategoryId.UNKNOWN -> R.drawable.unknown
        }
    }
}
