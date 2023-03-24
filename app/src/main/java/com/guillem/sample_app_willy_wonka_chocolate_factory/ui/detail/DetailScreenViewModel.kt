package com.guillem.sample_app_willy_wonka_chocolate_factory.ui.detail

import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.controllers.AppControllers
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.controllers.ViewController
import com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils.onFailure
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils.toUi
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.GetWorkerDetailedUseCase
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.UpdateWorkerDetailsUseCase
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.model.OompaLoompaUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Suppress("UnusedPrivateMember")
class DetailScreenViewModel(
    private val app: AppControllers,
    private val workerId: Int,
    private val getWorkerDetailsUseCase: GetWorkerDetailedUseCase,
    private val updateWorkerDetailsUseCase: UpdateWorkerDetailsUseCase
) : ViewController(app.coroutinesParams) {

    private val _workerData = MutableStateFlow(OompaLoompaUI())
    val workerData: StateFlow<OompaLoompaUI> = _workerData.asStateFlow()

    private val _failureData = MutableStateFlow<Int?>(null)
    val failureData: StateFlow<Int?> = _failureData.asStateFlow()

    init {
        getWorkerFromDatabase()
        updateWorkerDetails()
    }

    private fun getWorkerFromDatabase() {
        scope.launch {
            getWorkerDetailsUseCase(workerId).collect {
                _workerData.emit(it.toUi())
            }
        }
    }

    private fun updateWorkerDetails() {
        scope.launch {
            updateWorkerDetailsUseCase.invoke(workerId).onFailure {
                _failureData.emit(it.toUi())
            }
        }
    }

    fun onBackButtonClicked() {
        app.navigator.navUp()
    }
}

