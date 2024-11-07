package fr.dappli.portailfamilles.core.data.model.form

import kotlinx.serialization.Serializable

@Serializable
data class Theme(
    val name: String?,
    val subthemes: List<SubTheme?>?
)

@Serializable
data class SubTheme(
    val id: String?,
    val name: String?,
    val description: String?,
    val validate: Validate?
)

@Serializable
data class Validate(
    val begin: String?,
    val end: String?,
)
