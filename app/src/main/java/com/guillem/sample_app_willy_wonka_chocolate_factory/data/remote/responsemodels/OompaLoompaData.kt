package com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.responsemodels

import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.OompaLoompaEntity
import kotlinx.serialization.Serializable

@Serializable
data class OompaLoompaData(
    val age: Int,
    val country: String,
    val email: String,
    val favorite: FavoriteData,
    val first_name: String,
    val gender: String,
    val height: Int,
    val id: Int,
    val image: String,
    val last_name: String,
    val profession: String,
    val description: String? = null,
    val quota: String? = null,
) {
    fun toWorkerEntity() = OompaLoompaEntity(
        age = age,
        country = country,
        email = email,
        favorite = favorite.toFavoriteEntity(),
        first_name = first_name,
        gender = gender,
        height = height,
        id = id,
        image = image,
        last_name = last_name,
        profession = profession,
        description = description,
        quota = quota,
    )
}