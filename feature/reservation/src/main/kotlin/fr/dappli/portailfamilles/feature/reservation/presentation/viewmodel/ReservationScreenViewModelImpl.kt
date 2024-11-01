package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import fr.dappli.portailfamilles.core.data.remote.UserDataSourceImpl
import javax.inject.Inject

@HiltViewModel
class ReservationScreenViewModelImpl @Inject constructor(
    dataSourceImpl: UserDataSourceImpl
) : ReservationScreenViewModel() {

    override val dataSource: UserDataSource = dataSourceImpl
}
