package fr.dappli.portailfamilles.core.data.local.di

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.core.data.api.PersistentDataSource
import fr.dappli.portailfamilles.core.data.local.PersistentDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataLocalModule {

    @Provides
    @Singleton
    fun providePersistentDataSource(
        @ApplicationContext applicationContext: Context,
    ): PersistentDataSource {
        val preferencesDataStore = PreferenceDataStoreFactory.create {
            applicationContext.preferencesDataStoreFile(DATA_STORE_FILE_NAME)
        }
        return PersistentDataSourceImpl(preferencesDataStore)
    }

    private const val DATA_STORE_FILE_NAME = "fr.dappli.portailfamilles"
}
