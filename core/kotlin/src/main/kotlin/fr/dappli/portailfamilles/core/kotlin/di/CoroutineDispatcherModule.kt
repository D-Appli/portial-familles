package fr.dappli.portailfamilles.core.kotlin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.CoroutineDispatcherProvider
import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatcherModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(): DispatcherProvider = CoroutineDispatcherProvider()
}
