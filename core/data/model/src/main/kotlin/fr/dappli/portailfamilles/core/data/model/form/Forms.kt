package fr.dappli.portailfamilles.core.data.model.form

import kotlinx.serialization.Serializable

@Serializable
data class Forms(
    val code_retour: String?,
    val accueils: List<Form?>?
)
