package fr.dappli.portailfamilles.core.data.api

import kotlinx.coroutines.flow.Flow

interface PersistentDataSource {
    suspend fun getBoolean(key: String): Boolean?
    suspend fun getString(key: String): String?

    fun observeBoolean(key: String): Flow<Boolean>
    fun observeString(key: String): Flow<String>

    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun putString(key: String, value: String)

    suspend fun remove(key: String)
}

enum class PersistenceKey {
    USER_ID,
    TOKEN
}
