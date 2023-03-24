package com.guillem.sample_app_willy_wonka_chocolate_factory.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils.MyResult
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.AppDatabase
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.ApiEndpoints
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils.ApiErrorHandling
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils.GenericApiError
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model.OompaLoompa
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository.WorkersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


@OptIn(ExperimentalPagingApi::class)
class WorkersRepositoryImpl(
    private val api: ApiEndpoints, private val db: AppDatabase
) : WorkersRepository {

    private val backDispatcher = Dispatchers.IO

    override fun getWorkers(workerId: Unit): Flow<PagingData<OompaLoompa>> {
        val pagingSourceFactory = { db.workersDao().getWorkers() }

        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true,
            ), remoteMediator = WorkersRemoteMediator(
                api = api,
                db = db,
            ), pagingSourceFactory = pagingSourceFactory
        ).flow.map { workerEntityPagingData ->
            workerEntityPagingData.map { workerEntity -> workerEntity.toDomain() }
        }
    }

    override suspend fun getWorkerDetailed(workerId: Int): Flow<OompaLoompa> =
        withContext(backDispatcher) {
            with(db.workersDao()) {
                getWorkerById(workerId).map { it.toDomain() }
            }
        }

    override suspend fun updateWorkerDetailed(workerId: Int): MyResult<Unit, GenericApiError> =
        withContext(backDispatcher) {
            with(db.workersDao()) {
                if (!isWorkerDetailsComplete(workerId)) {
                    try {
                        val response = api.getWorkerDetailsById(workerId)
                        updateWorker(response.toWorkerEntity(workerId))
                    } catch (e: Exception) {
                        return@withContext ApiErrorHandling.handleError(e)
                    }
                }
                MyResult.Ok(Unit)
            }
        }
}

