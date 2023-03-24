package com.guillem.sample_app_willy_wonka_chocolate_factory.core.usecase

fun interface UseCase<Params, Return> {
    operator fun invoke(params: Params): Return
}
