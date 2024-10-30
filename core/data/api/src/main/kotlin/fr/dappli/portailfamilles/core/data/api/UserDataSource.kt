package fr.dappli.portailfamilles.core.data.api

interface UserDataSource {
    suspend fun getUser(userId: String, token: String): String // TODO add a model
}
