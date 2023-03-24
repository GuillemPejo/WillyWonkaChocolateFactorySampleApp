package com.guillem.sample_app_willy_wonka_chocolate_factory.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.scopedComposable
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.detail.DetailScreenDestination
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.home.HomeScreenDestination

object RootNavGraph {
    val StartDestination = HomeScreenDestination
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.rootNavGraph() {
    scopedComposable(HomeScreenDestination)
    scopedComposable(DetailScreenDestination)
}
