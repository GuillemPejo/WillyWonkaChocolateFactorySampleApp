package com.guillem.sample_app_willy_wonka_chocolate_factory.di

import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { AppDatabase.getInstance(androidContext()).remoteKeysDao() }
    factory { AppDatabase.getInstance(androidContext()).workersDao() }
    factory { AppDatabase.getInstance(androidContext()) }
}
