package com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.savestate

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

interface StateSaver {
    fun <T> getAutoSaveFlow(scope: CoroutineScope, key: String, default: T): MutableStateFlow<T>
    fun <T> getNullableAutoSaveFlow(
        scope: CoroutineScope,
        key: String,
        default: T?,
    ): MutableStateFlow<T?>

    fun <T> setValue(key: String, value: T)
    fun <T> getValue(key: String): T?
    fun <T> getValue(key: String, default: T): T

    fun <T> setNullableValue(key: String, value: T?)

    fun <T> getNullableValue(key: String): T?
    fun <T> getNullableValue(key: String, default: T?): T?
    fun unTrackKey(key: String)
}
