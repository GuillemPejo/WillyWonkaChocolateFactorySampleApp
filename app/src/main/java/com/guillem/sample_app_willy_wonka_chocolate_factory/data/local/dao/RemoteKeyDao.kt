package com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(key: List<RemoteKeyEntity>)

    @Query("SELECT * FROM RemoteKey WHERE id = :id")
    suspend fun getRemoteKeysById(id: Int): RemoteKeyEntity?

    @Query("DELETE FROM RemoteKey")
    suspend fun deleteAll()
}