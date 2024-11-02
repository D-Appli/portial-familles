package fr.dappli.portailfamilles.feature.reservation.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.data.api.FormsDataSource
import fr.dappli.portailfamilles.core.data.api.UserDataSource
import javax.inject.Inject

@HiltViewModel
class ReservationScreenViewModelImpl @Inject constructor(
    _userDataSourceImpl: UserDataSource,
    _formsDataSource: FormsDataSource,
) : ReservationScreenViewModel() {

    override val userDataSource: UserDataSource = _userDataSourceImpl
    override val formsDataSource: FormsDataSource = _formsDataSource
}
