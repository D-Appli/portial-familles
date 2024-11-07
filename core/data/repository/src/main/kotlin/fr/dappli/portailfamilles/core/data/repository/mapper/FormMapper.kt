package fr.dappli.portailfamilles.core.data.repository.mapper

import fr.dappli.portailfamilles.core.data.model.PageReturnCode
import fr.dappli.portailfamilles.core.data.model.form.Forms
import fr.dappli.portailfamilles.core.domain.model.exception.DomainException
import fr.dappli.portailfamilles.core.domain.model.form.Form
import fr.dappli.portailfamilles.core.domain.model.form.FormId
import fr.dappli.portailfamilles.core.kotlin.mapper.Mapper
import javax.inject.Inject

class FormMapper @Inject constructor(
    private val themeMapper: ThemeMapper
) : Mapper<Forms, List<Form>> {
    override fun map(param: Forms): List<Form> {
        if (param.code_retour != PageReturnCode.OK.name)
            throw DomainException.PageError("Unknown return code ${param.code_retour} on forms page")

        return param.accueils?.map { form ->
            // forms are mapped here to only known ones
            val formId = FormId.entries.find {
                it.id == form?.id_accueil
            }
            val categories = form?.themes?.let {
                themeMapper.map(it)
            } ?: emptyList()
            val label = form?.libelle
            if (formId != null && label != null) {
                Form(formId, label, categories)
            } else {
                null
            }
        }?.filterNotNull() ?: emptyList()
    }
}
