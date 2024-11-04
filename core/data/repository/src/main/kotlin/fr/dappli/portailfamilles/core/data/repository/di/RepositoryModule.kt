package fr.dappli.portailfamilles.core.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.core.data.repository.AuthenticationRepositoryImpl
import fr.dappli.portailfamilles.core.data.repository.FormRepositoryImpl
import fr.dappli.portailfamilles.core.data.repository.MyCityRepositoryImpl
import fr.dappli.portailfamilles.core.domain.irepository.AuthenticationRepository
import fr.dappli.portailfamilles.core.domain.irepository.FormRepository
import fr.dappli.portailfamilles.core.domain.irepository.MyCityRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideAuthenticationRepository(
        repository: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    fun provideFormRepository(
        repository: FormRepositoryImpl
    ): FormRepository

    @Binds
    fun provideMyCityRepository(
        repository: MyCityRepositoryImpl
    ): MyCityRepository
}
