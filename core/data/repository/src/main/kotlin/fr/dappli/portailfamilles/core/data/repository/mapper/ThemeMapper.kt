package fr.dappli.portailfamilles.core.data.repository.mapper

import fr.dappli.portailfamilles.core.data.model.form.Theme
import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.core.domain.model.form.SubCategory
import fr.dappli.portailfamilles.core.kotlin.mapper.Mapper
import fr.dappli.portailfamilles.core.kotlin.titleCase
import javax.inject.Inject

class ThemeMapper @Inject constructor(): Mapper<List<Theme>, List<Category>> {

    override fun map(param: List<Theme>): List<Category> {
        return param.map {
            val subCategories = it.subthemes?.map { subTheme ->
                SubCategory(
                    id = subTheme?.id ?: "",
                    name = subTheme?.name?.titleCase() ?: "",
                    description = subTheme?.description?.titleCase() ?: "",
                    startDate = subTheme?.validate?.begin ?: "",
                    endDate = subTheme?.validate?.end ?: "",
                )
            } ?: emptyList()
            Category(
                name = it.name?.titleCase() ?: "",
                items = subCategories
            )
        }
    }
}
