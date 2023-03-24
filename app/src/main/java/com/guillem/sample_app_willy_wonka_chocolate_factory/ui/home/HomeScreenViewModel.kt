package com.guillem.sample_app_willy_wonka_chocolate_factory.ui.home

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.controllers.AppControllers
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.controllers.ViewController
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.transition.NavTransition
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.GetWorkersUseCase
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.detail.DetailScreenDestination
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.model.OompaLoompaUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Suppress("UnusedPrivateMember")
class HomeScreenViewModel(
    private val app: AppControllers, private val getWorkersUseCase: GetWorkersUseCase
) : ViewController(app.coroutinesParams) {

    private val _workers = MutableStateFlow<PagingData<OompaLoompaUI>>(PagingData.empty())
    val workers: StateFlow<PagingData<OompaLoompaUI>> = _workers.asStateFlow()


    init {
        getAllWorkers()
    }

    fun onItemClick(workerId: Int) {
        app.navigator.goTo(
            DetailScreenDestination(workerId), transition = NavTransition.SharedAxisX
        )
    }

    private fun getAllWorkers() {
        scope.launch {
            getWorkersUseCase(Unit).cachedIn(scope).collect {
                _workers.value = it.map { worker -> worker.toUi() }
            }
        }
    }
}

