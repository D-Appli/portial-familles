package fr.dappli.portailfamilles.core.data.repository.mapper

import fr.dappli.portailfamilles.core.data.model.form.Theme
import fr.dappli.portailfamilles.core.domain.model.form.Category
import fr.dappli.portailfamilles.core.domain.model.form.CategoryId
import fr.dappli.portailfamilles.core.domain.model.form.SubCategory
import fr.dappli.portailfamilles.core.kotlin.mapper.Mapper
import fr.dappli.portailfamilles.core.kotlin.titleCase
import javax.inject.Inject

class ThemeMapper @Inject constructor(
    private val categoryIdMapper: CategoryIdMapper
): Mapper<List<Theme>, List<Category>> {

    override fun map(param: List<Theme>): List<Category> {
        return param.map {
            val subCategories = it.subthemes?.mapNotNull { subTheme ->
                subTheme?.id?.let { id ->
                    SubCategory(
                        id = id,
                        name = subTheme.name?.titleCase() ?: "",
                        description = subTheme.description?.titleCase() ?: "",
                        startDate = subTheme.validate?.begin ?: "",
                        endDate = subTheme.validate?.end ?: "",
                    )
                }
            } ?: emptyList()
            Category(
                categoryId = categoryIdMapper.map(it.name),
                name = it.name?.titleCase() ?: "",
                items = subCategories
            )
        }
    }

    private fun map(categoryName: String): CategoryId {
        return CategoryId.entries.find {
            it.code.equals(categoryName.trim(), ignoreCase = true)
        } ?: CategoryId.UNKNOWN
    }
}
