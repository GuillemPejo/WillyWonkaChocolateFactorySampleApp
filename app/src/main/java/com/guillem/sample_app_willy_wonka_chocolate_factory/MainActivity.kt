package com.guillem.sample_app_willy_wonka_chocolate_factory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.theme.WillyWonkaChocolateFactoryTheme
import com.guillem.sample_app_willy_wonka_chocolate_factory.presentation.AppLayout

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            WillyWonkaChocolateFactoryTheme() {
                AppLayout()
            }
        }
    }
}
