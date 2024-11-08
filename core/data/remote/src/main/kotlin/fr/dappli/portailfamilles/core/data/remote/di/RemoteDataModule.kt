package fr.dappli.portailfamilles.core.data.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.core.data.api.FormsDataSource
import fr.dappli.portailfamilles.core.data.api.MyCityDataSource
import fr.dappli.portailfamilles.core.data.api.SignInDataSource
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.data.remote.FormsDataSourceImpl
import fr.dappli.portailfamilles.core.data.remote.MyCityDataSourceImpl
import fr.dappli.portailfamilles.core.data.remote.SignInDataSourceImpl
import fr.dappli.portailfamilles.core.data.remote.UserDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataModule {

    @Binds
    fun provideUserDataSource(repository: UserDataSourceImpl): UserDataSource

    @Binds
    fun provideSignInDataSource(repository: SignInDataSourceImpl): SignInDataSource

    @Binds
    fun provideFormsDataSource(repository: FormsDataSourceImpl): FormsDataSource

    @Binds
    fun provideMyCityDataSource(repository: MyCityDataSourceImpl): MyCityDataSource
}
