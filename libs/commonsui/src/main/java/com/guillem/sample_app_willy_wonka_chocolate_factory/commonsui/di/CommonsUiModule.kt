package com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.di

import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.controllers.AppControllers
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.Navigator
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val commonsUiModule = module {
    single { Navigator() }
    factoryOf(::AppControllers)
}
