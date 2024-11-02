package fr.dappli.portailfamilles.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import fr.dappli.portailfamilles.core.data.api.PersistentDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersistentDataSourceImpl @Inject constructor(
    private val preferences: DataStore<Preferences>
) : PersistentDataSource {

    private val data: Flow<Preferences>
        get() = preferences.data

    override suspend fun getBoolean(key: String): Boolean? {
        return data.firstOrNull()?.get(booleanPreferencesKey(key))
    }

    override suspend fun getString(key: String): String? {
        return data.firstOrNull()?.get(stringPreferencesKey(key))
    }

    override fun observeBoolean(key: String): Flow<Boolean?> {
        return getDataFlowFromKey(booleanPreferencesKey(key))
    }

    override fun observeString(key: String): Flow<String?> {
        return getDataFlowFromKey(stringPreferencesKey(key))
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        preferences.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun putString(key: String, value: String) {
        preferences.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun remove(key: String) {
        preferences.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
        }
    }

    private fun <T> getDataFlowFromKey(preferencesKey: Preferences.Key<T>): Flow<T?> = data
        .map { preferences -> preferences[preferencesKey] }
}
