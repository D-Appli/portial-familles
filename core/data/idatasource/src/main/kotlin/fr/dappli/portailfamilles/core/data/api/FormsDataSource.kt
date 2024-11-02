package fr.dappli.portailfamilles.core.data.api

import fr.dappli.portailfamilles.core.data.model.form.Forms

// Formularies
interface FormsDataSource {
    suspend fun getForms(userId: String): Forms
}
