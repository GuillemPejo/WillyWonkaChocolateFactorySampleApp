package com.guillem.sample_app_willy_wonka_chocolate_factory.presentation

import androidx.compose.runtime.Composable

@Composable
fun AppLayout() {
    NavigationHost(startDestinationPath = RootNavGraph.StartDestination.route) {
        rootNavGraph()
    }
}
