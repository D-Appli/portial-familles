package fr.dappli.portailfamilles.core.data.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.core.data.api.SignInDataSource
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.data.remote.SignInDataSourceImpl
import fr.dappli.portailfamilles.core.data.remote.UserDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataModule {

    @Binds
    fun provideUserDataSource(repository: UserDataSourceImpl): UserDataSource

    @Binds
    fun provideSignInDataSource(repository: SignInDataSourceImpl): SignInDataSource
}
