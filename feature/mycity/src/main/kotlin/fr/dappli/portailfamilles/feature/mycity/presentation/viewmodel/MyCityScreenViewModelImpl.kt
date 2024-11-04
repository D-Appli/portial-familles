package fr.dappli.portailfamilles.feature.mycity.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dappli.portailfamilles.core.domain.usecase.GetRestaurantsUseCase
import fr.dappli.portailfamilles.core.kotlin.coroutines.providers.DispatcherProvider
import fr.dappli.portailfamilles.feature.mycity.presentation.model.CallToActions
import fr.dappli.portailfamilles.feature.mycity.presentation.model.MyCityScreenState
import fr.dappli.portailfamilles.feature.mycity.presentation.reducer.MyCityScreenAction
import fr.dappli.portailfamilles.feature.mycity.presentation.reducer.MyCityScreenReducer
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCityScreenViewModelImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val reducer: MyCityScreenReducer,
    private val getRestaurantsUseCase: GetRestaurantsUseCase
) : MyCityScreenViewModel() {

    override val stateFlow: StateFlow<MyCityScreenState> = reducer.stateFlow

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                reducer.update(MyCityScreenAction.SetLoading)
                val restaurants = getRestaurantsUseCase(offset = 0)
                reducer.update(MyCityScreenAction.SetContent(restaurants, bindCallToAction()))
            } catch (e: Throwable) {
                println("getRestaurantsUseCase error $e")
                reducer.update(MyCityScreenAction.SetError)
            }
        }
    }

    private fun bindCallToAction() = CallToActions(
        loadMoreItems = ::loadMoreItems,
    )

    private fun loadMoreItems(offset: Int) {
        viewModelScope.launch(dispatcherProvider.io) {
            reducer.update(MyCityScreenAction.SetLoading)
            val restaurants = getRestaurantsUseCase(offset)
            reducer.update(MyCityScreenAction.AddContent(restaurants))
        }
    }
}
