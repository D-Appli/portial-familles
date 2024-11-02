package fr.dappli.portailfamilles.core.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.core.data.repository.AuthenticationRepositoryImpl
import fr.dappli.portailfamilles.core.domain.irepository.AuthenticationRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideAuthenticationRepository(
        repository: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}
