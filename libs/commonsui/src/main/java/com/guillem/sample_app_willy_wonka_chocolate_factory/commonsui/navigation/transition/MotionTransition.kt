package com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.navigation.transition

import androidx.compose.runtime.Immutable

@Immutable
data class MotionTransition(
    val transitionSlide: Int,
    val transitionInitialScale: Float,
    val transitionTargetScale: Float,
    val transitionDuration: Long,
)
