package com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model.OompaLoompa
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model.OompaLoompa.*
import kotlinx.serialization.Serializable

@Entity(tableName = "Workers")
data class OompaLoompaEntity(
    val age: Int,
    val country: String,
    val email: String,
    val favorite: FavoriteEntity,
    val first_name: String,
    val gender: String,
    val height: Int,
    @PrimaryKey
    val id: Int,
    val image: String,
    var description: String?,
    var quota: String?,
    val last_name: String,
    val profession: String,
) {
    @Serializable
    data class FavoriteEntity(
        val color: String,
        val food: String,
        val random_string: String,
        val song: String,
    ) {
        fun toDomain() = Favorite(
            color = color,
            food = food,
            random_string = random_string,
            song = song
        )
    }
    fun toDomain() = OompaLoompa(
        age = age,
        country = country,
        email = email,
        favorite = favorite.toDomain(),
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
