package com.guillem.sample_app_willy_wonka_chocolate_factory.data.local

import androidx.room.TypeConverter
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.OompaLoompaEntity.FavoriteEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromJson(string: String): FavoriteEntity =  Json.decodeFromString(string)

    @TypeConverter
    fun toJson(entity: FavoriteEntity): String =  Json.encodeToString(entity)

}