package com.guillem.sample_app_willy_wonka_chocolate_factory

import android.app.Application
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.di.commonsUiModule
import com.guillem.sample_app_willy_wonka_chocolate_factory.core.di.coreModule
import com.guillem.sample_app_willy_wonka_chocolate_factory.di.appModule
import com.guillem.sample_app_willy_wonka_chocolate_factory.di.databaseModule
import com.guillem.sample_app_willy_wonka_chocolate_factory.di.networkModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

@ExperimentalCoroutinesApi
class BaseApplication : Application() {
    private val applicationScope: CoroutineScope by inject()

    override fun onCreate() {
        super.onCreate()
        setupDi()

        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setupDi() {
        startKoin {
            androidContext(this@BaseApplication)
            appModule.single { applicationScope }
            modules(
                listOf(
                    appModule,
                    coreModule,
                    commonsUiModule,
                    networkModule,
                    databaseModule,
                )
            )
        }
    }
}
