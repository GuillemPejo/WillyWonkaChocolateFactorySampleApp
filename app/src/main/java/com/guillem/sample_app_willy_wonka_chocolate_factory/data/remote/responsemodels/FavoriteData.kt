package com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.responsemodels

import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.OompaLoompaEntity
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteData(
    val color: String,
    val food: String,
    val random_string: String,
    val song: String,
){

    fun toFavoriteEntity() = OompaLoompaEntity.FavoriteEntity(
        color = color,
        food = food,
        random_string = random_string,
        song = song,
    )
}