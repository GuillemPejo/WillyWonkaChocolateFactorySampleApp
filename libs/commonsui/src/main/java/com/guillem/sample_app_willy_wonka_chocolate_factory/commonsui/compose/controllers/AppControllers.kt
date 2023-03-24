package com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.controllers

import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.Navigator
import com.guillem.sample_app_willy_wonka_chocolate_factory.core.app.FrontCoroutinesParams
import kotlinx.coroutines.CoroutineScope

data class AppControllers(
    val appScope: CoroutineScope,
    val coroutinesParams: FrontCoroutinesParams,
    val navigator: Navigator,
)
