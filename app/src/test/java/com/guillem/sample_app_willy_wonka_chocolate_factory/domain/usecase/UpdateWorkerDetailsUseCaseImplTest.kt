package com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase

import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository.WorkersRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateWorkerDetailsUseCaseImplTest {

    @RelaxedMockK
    private lateinit var workersRepository: WorkersRepository

    private lateinit var updateWorkerDetailsUseCase: UpdateWorkerDetailsUseCaseImpl

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        updateWorkerDetailsUseCase = UpdateWorkerDetailsUseCaseImpl(workersRepository)
    }

    @Test
    fun `when use case is invoked, should call updateWorkerDetailsUseCase`() = runBlocking {
        // given

        // when
        updateWorkerDetailsUseCase(1)

        // then
        coVerify(exactly = 1) { workersRepository.updateWorkerDetailed(any()) }
    }
}