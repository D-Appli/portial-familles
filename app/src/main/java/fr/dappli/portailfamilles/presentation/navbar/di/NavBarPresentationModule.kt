package fr.dappli.portailfamilles.presentation.navbar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.presentation.navbar.reducer.NavBarReducer
import fr.dappli.portailfamilles.presentation.navbar.reducer.NavBarReducerImpl

@Module
@InstallIn(SingletonComponent::class)
interface NavBarPresentationModule {

    @Binds
    fun provideNavBarReducer(reducer: NavBarReducerImpl): NavBarReducer
}
