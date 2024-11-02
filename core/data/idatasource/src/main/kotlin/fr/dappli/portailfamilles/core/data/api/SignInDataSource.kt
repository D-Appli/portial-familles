package fr.dappli.portailfamilles.core.data.api

interface SignInDataSource {
    fun clearAuthToken()
    suspend fun signIn(username: String, password: String, captcha: String)
}
