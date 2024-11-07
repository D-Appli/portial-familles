package fr.dappli.portailfamilles.core.data.repository.mapper

import fr.dappli.portailfamilles.core.domain.model.form.CategoryId
import fr.dappli.portailfamilles.core.kotlin.mapper.Mapper
import javax.inject.Inject

class CategoryIdMapper @Inject constructor(): Mapper<String?, CategoryId> {
    override fun map(param: String?): CategoryId {
        val name = param ?: return CategoryId.UNKNOWN
        return CategoryId.entries.find {
            it.code.equals(name.trim(), ignoreCase = true)
        } ?: CategoryId.UNKNOWN
    }
}
