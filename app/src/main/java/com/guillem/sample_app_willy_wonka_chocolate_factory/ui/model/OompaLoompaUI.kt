package com.guillem.sample_app_willy_wonka_chocolate_factory.ui.model

import kotlinx.serialization.Serializable


@Serializable
data class OompaLoompaUI(
    val age: Int = 0,
    val country: String = "",
    val email: String = "",
    val favorite: FavoriteUI = FavoriteUI(),
    val completeName: Pair<String, String> = Pair("",""),
    val gender: String = "",
    val height: Int = 0,
    val id: Int = 0,
    val image: String = "",
    val profession: String = "",
    val description: String? = "",
    val quota: String? = "",
) {
    @Serializable
    data class FavoriteUI(
        val color: String = "",
        val food: String = "",
        val random_string: String = "",
        val song: String = "",
    )
}
