package fr.dappli.portailfamilles.presentation.navbar.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.domain.usecase.GetFormsUseCase
import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.presentation.navbar.model.NavBarState
import fr.dappli.portailfamilles.presentation.navbar.reducer.NavBarAction
import fr.dappli.portailfamilles.presentation.navbar.reducer.NavBarReducer
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavBarViewModelImpl @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val getFormsUseCase: GetFormsUseCase,
    reducer: NavBarReducer,
) : NavBarViewModel() {

    override val stateFlow: StateFlow<NavBarState> = reducer.stateFlow

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val forms = getFormsUseCase()
                reducer.update(NavBarAction.SetContent(forms))
            } catch (e: Throwable) {
                println("getFormsUseCase error $e")
                reducer.update(NavBarAction.SetError)
            }
        }
    }
}
