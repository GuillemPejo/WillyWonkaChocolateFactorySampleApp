package com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase

import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model.OompaLoompa
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository.WorkersRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetWorkerDetailedUseCaseImplTest {

    @RelaxedMockK
    private lateinit var workersRepository: WorkersRepository

    private lateinit var getWorkersDetailsUseCase: GetWorkerDetailedUseCaseImpl

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getWorkersDetailsUseCase = GetWorkerDetailedUseCaseImpl(workersRepository)
    }

    @Test
    fun `when use case is invoked, repository should return a flow of OompaLoompasWorkers`() = runBlocking {
        // given
        val expected = mockk<Flow<OompaLoompa>>()
        coEvery { workersRepository.getWorkerDetailed(any()) } returns expected

        // when
        val result = getWorkersDetailsUseCase(1)

        // then
        assertEquals(expected, result)
    }
}

