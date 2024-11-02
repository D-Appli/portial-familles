package fr.dappli.portailfamilles.core.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignIn(
    val username: String,
    val password: String,
    val captcha: String,
)
