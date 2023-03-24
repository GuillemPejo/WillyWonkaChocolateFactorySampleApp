package com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository

import androidx.paging.PagingData
import com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils.MyResult
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils.GenericApiError
import kotlinx.coroutines.flow.Flow
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model.OompaLoompa

interface WorkersRepository {
    fun getWorkers(workerId: Unit): Flow<PagingData<OompaLoompa>>
    suspend fun getWorkerDetailed(workerId: Int): Flow<OompaLoompa>
    suspend fun updateWorkerDetailed(workerId: Int): MyResult<Unit, GenericApiError>
}