package fr.dappli.portailfamilles.server.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.server.Server
import fr.dappli.portailfamilles.server.ServerImpl

@Module
@InstallIn(SingletonComponent::class)
interface ServerModule {

    @Binds
    fun provideServer(server: ServerImpl): Server
}
