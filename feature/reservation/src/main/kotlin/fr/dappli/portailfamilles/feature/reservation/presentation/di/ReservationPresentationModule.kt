package fr.dappli.portailfamilles.feature.reservation.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dappli.portailfamilles.feature.reservation.presentation.reducer.ReservationScreenReducer
import fr.dappli.portailfamilles.feature.reservation.presentation.reducer.ReservationScreenReducerImpl
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModel
import fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel.ReservationScreenViewModelImpl


@Module
@InstallIn(SingletonComponent::class)
interface ReservationPresentationModule {

    @Binds
    fun provideReservationScreenReducer(reducer: ReservationScreenReducerImpl): ReservationScreenReducer

    @Binds
    fun provideReservationScreenViewModel(viewModel: ReservationScreenViewModelImpl): ReservationScreenViewModel
}