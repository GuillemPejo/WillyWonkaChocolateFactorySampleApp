package com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase

import com.guillem.sample_app_willy_wonka_chocolate_factory.core.usecase.UseCaseSuspend
import com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils.MyResult
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils.GenericApiError
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository.WorkersRepository


interface UpdateWorkerDetailsUseCase : UseCaseSuspend<Int, MyResult<Unit, GenericApiError>>

class UpdateWorkerDetailsUseCaseImpl(
    private val workersRepository: WorkersRepository,
) : UpdateWorkerDetailsUseCase {

    override suspend fun invoke(params: Int): MyResult<Unit, GenericApiError> =
        workersRepository.updateWorkerDetailed(params)
}