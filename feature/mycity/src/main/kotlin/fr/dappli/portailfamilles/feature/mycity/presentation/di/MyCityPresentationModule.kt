package fr.dappli.portailfamilles.feature.mycity.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.feature.mycity.presentation.reducer.MyCityScreenReducer
import fr.dappli.portailfamilles.feature.mycity.presentation.reducer.MyCityScreenReducerImpl
import fr.dappli.portailfamilles.feature.mycity.presentation.viewmodel.MyCityScreenViewModel
import fr.dappli.portailfamilles.feature.mycity.presentation.viewmodel.MyCityScreenViewModelImpl

@Module
@InstallIn(SingletonComponent::class)
interface MyCityPresentationModule {

    @Binds
    fun provideMyCityScreenReducer(reducer: MyCityScreenReducerImpl): MyCityScreenReducer

    @Binds
    fun provideMyCityScreenViewModel(viewModel: MyCityScreenViewModelImpl): MyCityScreenViewModel
}
