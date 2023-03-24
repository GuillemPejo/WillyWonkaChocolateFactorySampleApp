package com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.OompaLoompaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkersDao {

    @Query("SELECT * FROM Workers WHERE id = :workerId")
    fun getWorkerById(workerId: Int): Flow<OompaLoompaEntity>

    @Query("SELECT * FROM Workers")
    fun getWorkers(): PagingSource<Int, OompaLoompaEntity>

    @Query("SELECT COUNT(*) FROM Workers WHERE id = :workerId AND description IS NOT NULL AND quota IS NOT NULL")
    fun isWorkerDetailsComplete(workerId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkers(workersList: List<OompaLoompaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorker(worker: OompaLoompaEntity)

    @Query("DELETE FROM Workers")
    suspend fun deleteAll()
}
