package fr.dappli.portailfamilles.core.data.api

interface LoginDataSource {
    suspend fun login(username: String, password: String)
}
