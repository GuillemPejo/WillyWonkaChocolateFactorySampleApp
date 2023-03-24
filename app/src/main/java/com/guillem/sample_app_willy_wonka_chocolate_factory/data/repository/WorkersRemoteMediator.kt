package com.guillem.sample_app_willy_wonka_chocolate_factory.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.AppDatabase
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.RemoteKeyEntity
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.OompaLoompaEntity
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.ApiEndpoints
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class WorkersRemoteMediator(
    private val db: AppDatabase,
    private val api: ApiEndpoints
) : RemoteMediator<Int, OompaLoompaEntity>() {

    override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, OompaLoompaEntity>
    ): MediatorResult {

        try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = state.getRemoteKeyClosestToCurrentPosition()
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = state.getFirstRemoteKey()
                    val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = state.getLastRemoteKey()
                    val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextKey
                }
            }

            val response = api.getWorkers(currentPage)
            val endOfPaginationReached = currentPage == response.total

            val prevPage = if (currentPage == 1) null else currentPage.minus(1)
            val nextPage = if (endOfPaginationReached) null else currentPage.plus(1)

            db.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    db.workersDao().deleteAll()
                    db.remoteKeysDao().deleteAll()
                }

                db.workersDao().insertWorkers(response.results.map { it.toWorkerEntity() })

                val keys = response.results.map {
                    RemoteKeyEntity(
                        it.id,
                        prevKey = prevPage,
                        nextKey = nextPage
                    )
                }
                db.remoteKeysDao().insertAll(keys)
            }

            return MediatorResult.Success(endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }


    private suspend fun PagingState<Int, OompaLoompaEntity>.getRemoteKeyClosestToCurrentPosition(): RemoteKeyEntity? =
        anchorPosition?.let { position ->
            closestItemToPosition(position)?.id?.let { key ->
                db.remoteKeysDao().getRemoteKeysById(key)
            }
        }

    private suspend fun PagingState<Int, OompaLoompaEntity>.getLastRemoteKey(): RemoteKeyEntity? =
        pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { key -> db.remoteKeysDao().getRemoteKeysById(key.id) }

    private suspend fun PagingState<Int, OompaLoompaEntity>.getFirstRemoteKey(): RemoteKeyEntity? =
        pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { key -> db.remoteKeysDao().getRemoteKeysById(key.id) }
}