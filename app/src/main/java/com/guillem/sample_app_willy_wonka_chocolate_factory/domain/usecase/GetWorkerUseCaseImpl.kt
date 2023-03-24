package com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase

import com.guillem.sample_app_willy_wonka_chocolate_factory.core.usecase.UseCaseSuspend
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model.OompaLoompa
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository.WorkersRepository
import kotlinx.coroutines.flow.Flow

interface GetWorkerDetailedUseCase : UseCaseSuspend<Int, Flow<OompaLoompa>>

class GetWorkerDetailedUseCaseImpl(
    private val workersRepository: WorkersRepository,
) : GetWorkerDetailedUseCase {

    override suspend fun invoke(params: Int): Flow<OompaLoompa> =
        workersRepository.getWorkerDetailed(params)
}
