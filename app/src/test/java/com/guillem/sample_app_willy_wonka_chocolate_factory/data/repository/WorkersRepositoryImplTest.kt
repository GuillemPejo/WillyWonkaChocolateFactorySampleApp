package com.guillem.sample_app_willy_wonka_chocolate_factory.data.repository

import com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils.MyResult
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.AppDatabase
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.ApiEndpoints
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils.NoConnectivityException
import com.guillem.sample_app_willy_wonka_chocolate_factory.fake.FakePostsPagingSource
import com.guillem.sample_app_willy_wonka_chocolate_factory.fake.fakeOompaLoompaEntity
import com.guillem.sample_app_willy_wonka_chocolate_factory.fake.fakeOompaLoompaRemote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WorkersRepositoryImplTest{

    @RelaxedMockK
    private lateinit var apiEndpoints: ApiEndpoints

    @RelaxedMockK
    private lateinit var appDatabase: AppDatabase

    private lateinit var workersRepository: WorkersRepositoryImpl

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        workersRepository = WorkersRepositoryImpl(apiEndpoints, appDatabase)
    }

    @Test
    fun `getWorkers returns expected flow of paging data`() = runTest {
        // Given
        val pagingSource = FakePostsPagingSource()
        every { appDatabase.workersDao().getWorkers() } returns pagingSource

        // When
        val result = workersRepository.getWorkers(Unit).toList()

        // Then
        print(result)
        coVerify { appDatabase.workersDao().getWorkers() }
        assert(result.size == 1)
    }

    @Test
    fun `getWorkerDetailed returns expected flow of OompaLoompasWorker`() = runTest {
        // Given
        val workerEntity = fakeOompaLoompaEntity
        every { appDatabase.workersDao().getWorkerById(any())} returns flowOf(workerEntity)

        // When
        val result = workersRepository.getWorkerDetailed(1).first()

        // Then
        assert(result == workerEntity.toDomain())
    }

    @Test
    fun `updateWorkerDetailed updates database on API success and return MyResult Ok`() = runTest {
        // Given
        val workerId = 1
        val remoteWorker = fakeOompaLoompaRemote
        val remoteWorkerEntity = remoteWorker.toWorkerEntity(workerId)

        every { appDatabase.workersDao().isWorkerDetailsComplete(workerId)} returns false
        coEvery { apiEndpoints.getWorkerDetailsById(workerId)} returns remoteWorker
        coEvery { appDatabase.workersDao().updateWorker(remoteWorkerEntity)} returns Unit

        // When
        workersRepository.updateWorkerDetailed(workerId)

        // Then
        coVerify(exactly = 1) { appDatabase.workersDao().isWorkerDetailsComplete(workerId)}
        coVerify(exactly = 1) { apiEndpoints.getWorkerDetailsById(workerId)}
        coVerify(exactly = 1) { appDatabase.workersDao().updateWorker(remoteWorkerEntity)}
    }

    @Test
    fun `updateWorkerDetailed should return MyResult Err with NoConnectivityException when API call fails`() = runTest {
        // Given
        val workerId = 1

        every { appDatabase.workersDao().isWorkerDetailsComplete(workerId)} returns false
        coEvery { apiEndpoints.getWorkerDetailsById(workerId)} throws NoConnectivityException()


        // When
        val result = workersRepository.updateWorkerDetailed(workerId)

        // Then
        coVerify(exactly = 1) { appDatabase.workersDao().isWorkerDetailsComplete(workerId)}
        coVerify(exactly = 1) { apiEndpoints.getWorkerDetailsById(workerId)}

        assert(result is MyResult.Err) { NoConnectivityException() }
    }

    @Test
    fun `isWorkerDetailsComplete is true so it doesn't do API call nor does it updates database`() = runTest {
        // Given
        val workerId = 1
        every { appDatabase.workersDao().isWorkerDetailsComplete(workerId)} returns true

        // When
        val result = workersRepository.updateWorkerDetailed(workerId)

        // Then
        coVerify(exactly = 1) { appDatabase.workersDao().isWorkerDetailsComplete(workerId) }
        coVerify(exactly = 0) { apiEndpoints.getWorkerDetailsById(workerId) }
        assert(result is MyResult.Ok)
    }

}