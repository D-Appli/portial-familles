package fr.dappli.portailfamilles.feature.indentity.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.feature.indentity.presentation.reducer.IdentityScreenReducer
import fr.dappli.portailfamilles.feature.indentity.presentation.reducer.IdentityScreenReducerImpl

@Module
@InstallIn(SingletonComponent::class)
interface IdentityPresentationModule {

    @Binds
    fun provideIdentityScreenReducer(
        identityScreenReducerImpl: IdentityScreenReducerImpl
    ): IdentityScreenReducer
}
