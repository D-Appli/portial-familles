package fr.dappli.portailfamilles.core.data.model.form

import kotlinx.serialization.Serializable

@Serializable
data class Form(
    val id_accueil: String?,
    val libelle: String?,
    val order: String?,
    val couleur: String?
)
