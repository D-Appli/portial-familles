package fr.dappli.portailfamilles.core.domain.irepository

import fr.dappli.portailfamilles.core.domain.model.form.Form

interface FormRepository {
    suspend fun getForms(forceRefresh: Boolean = false): List<Form>
}
