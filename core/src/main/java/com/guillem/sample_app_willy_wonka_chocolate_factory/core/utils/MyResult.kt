package com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils

sealed class MyResult<out V, out E> {
    data class Ok<out V>(val value: V) : MyResult<V, Nothing>()
    data class Err<out E>(val error: E) : MyResult<Nothing, E>()
}

inline infix fun <V, E> MyResult<V, E>.onSuccess(action: (V) -> Unit): MyResult<V, E> {
    if (this is MyResult.Ok) {
        action(value)
    }

    return this
}

inline infix fun <V, E> MyResult<V, E>.onFailure(action: (E) -> Unit): MyResult<V, E> {
    if (this is MyResult.Err) {
        action(error)
    }
    return this
}