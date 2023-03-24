package com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase

import androidx.paging.PagingData
import com.guillem.sample_app_willy_wonka_chocolate_factory.core.usecase.UseCaseSuspend
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository.WorkersRepository
import kotlinx.coroutines.flow.Flow
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model.OompaLoompa

interface GetWorkersUseCase : UseCaseSuspend<Unit, Flow<PagingData<OompaLoompa>>>

class GetWorkersUseCaseImpl(
    private val workersRepository: WorkersRepository,
) : GetWorkersUseCase {

    override suspend fun invoke(params: Unit): Flow<PagingData<OompaLoompa>> =
        workersRepository.getWorkers(params)

}
