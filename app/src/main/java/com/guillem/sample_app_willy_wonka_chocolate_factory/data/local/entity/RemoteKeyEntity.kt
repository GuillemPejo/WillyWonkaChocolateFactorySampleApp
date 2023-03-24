package com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKey")
data class RemoteKeyEntity(
    @PrimaryKey val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
