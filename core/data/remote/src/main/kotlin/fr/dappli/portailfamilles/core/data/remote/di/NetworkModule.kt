package fr.dappli.portailfamilles.core.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.core.data.remote.network.Network
import fr.dappli.portailfamilles.core.data.remote.network.NetworkImpl
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideNetwork() : Network = NetworkImpl()

    @Provides
    @Singleton
    fun provideHttpClient(network: Network) : HttpClient = network.client
}
