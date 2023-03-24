package com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation

sealed class Route {
    open class Forward(
        val destination: String,
        val popStrategy: NavPop = NavPop.Nothing,
        val launchSingleTop: Boolean = false,
    ) : Route()
    object Back : Route()
    object Up : Route() // the in-app back arrow does not always behave like system back
}
