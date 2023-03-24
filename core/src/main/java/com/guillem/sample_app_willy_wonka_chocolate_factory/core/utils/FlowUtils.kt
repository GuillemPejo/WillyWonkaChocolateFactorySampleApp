package com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

fun <T, K> StateFlow<T>.mapState(
    scope: CoroutineScope,
    transform: (data: T) -> K,
    started: SharingStarted = SharingStarted.WhileSubscribed(),
): StateFlow<K> = mapLatest { transform(it) }
    .stateIn(scope, started, transform(value))
