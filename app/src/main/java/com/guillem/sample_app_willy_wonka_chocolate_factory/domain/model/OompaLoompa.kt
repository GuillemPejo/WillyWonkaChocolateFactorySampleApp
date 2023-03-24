package com.guillem.sample_app_willy_wonka_chocolate_factory.domain.model

import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.model.OompaLoompaUI
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.model.OompaLoompaUI.FavoriteUI
import kotlinx.serialization.Serializable


@Serializable
data class OompaLoompa(
    val age: Int,
    val country: String,
    val email: String,
    val favorite: Favorite,
    val first_name: String,
    val gender: String,
    val height: Int,
    val id: Int,
    val description: String?,
    val quota: String?,
    val image: String,
    val last_name: String,
    val profession: String,
) {
    @Serializable
    data class Favorite(
        val color: String,
        val food: String,
        val random_string: String,
        val song: String,
    ) {
        fun toUi() = FavoriteUI(color = color, food = food, random_string = random_string, song = song)
    }

    fun toUi() = OompaLoompaUI(
        age = age,
        country = country,
        email = email,
        favorite = favorite.toUi(),
        completeName = Pair(first_name, last_name),
        gender = gender,
        height = height,
        id = id,
        image = image,
        profession = profession,
        description = description,
        quota = quota,
    )
}
