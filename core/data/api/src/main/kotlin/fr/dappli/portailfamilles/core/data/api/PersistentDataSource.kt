package fr.dappli.portailfamilles.core.data.api

interface PersistentDataSource {
    suspend fun getBoolean(key: String): Boolean?
    suspend fun putBoolean(key: String, value: Boolean)

    suspend fun getString(key: String): String?
    suspend fun putString(key: String, value: String)

    suspend fun remove(key: String)
}

enum class PersistenceKey {
    USER_ID,
    TOKEN
}
