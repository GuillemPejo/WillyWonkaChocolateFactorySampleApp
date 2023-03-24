package com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase

import androidx.paging.PagingData
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model.OompaLoompa
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository.WorkersRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetWorkersUseCaseImplTest {

    @RelaxedMockK
    private lateinit var workersRepository: WorkersRepository

    private lateinit var getWorkersUseCase: GetWorkersUseCaseImpl

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getWorkersUseCase = GetWorkersUseCaseImpl(workersRepository)
    }

    @Test
    fun `when use case is invoked, repository should return a flow of paging data of OompaLoompasWorkers`() = runBlocking {
        // given
        val expected = mockk<Flow<PagingData<OompaLoompa>>>()
        every { workersRepository.getWorkers(Unit) } returns expected

        // when
        val result = getWorkersUseCase(Unit)

        // then
        assertEquals(expected, result)
    }
}