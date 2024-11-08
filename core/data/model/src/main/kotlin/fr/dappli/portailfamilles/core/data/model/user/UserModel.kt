package fr.dappli.portailfamilles.core.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val code_retour: String?,
    val nom: String?,
    val prenom: String?,
    val mail: String?,
    val civilite: String?
)
