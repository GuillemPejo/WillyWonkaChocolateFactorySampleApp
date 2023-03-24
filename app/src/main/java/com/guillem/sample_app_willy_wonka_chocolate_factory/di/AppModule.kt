package com.guillem.sample_app_willy_wonka_chocolate_factory.di

import com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils.Cancellable
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.repository.WorkersRepositoryImpl
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.repository.WorkersRepository
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.GetWorkerDetailedUseCase
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.GetWorkerDetailedUseCaseImpl
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.GetWorkersUseCase
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.GetWorkersUseCaseImpl
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.UpdateWorkerDetailsUseCase
import com.guillem.sample_app_willy_wonka_chocolate_factory.domain.usecase.UpdateWorkerDetailsUseCaseImpl
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.detail.DetailScreenDestination
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.detail.DetailScreenViewModel
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.home.HomeScreenDestination
import com.guillem.sample_app_willy_wonka_chocolate_factory.ui.home.HomeScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.scopedOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    scope<HomeScreenDestination> {
        scopedOf(::HomeScreenViewModel) bind Cancellable::class
    }

    scope<DetailScreenDestination> {
        scopedOf(::DetailScreenViewModel) bind Cancellable::class
    }

    factoryOf(::GetWorkersUseCaseImpl) bind GetWorkersUseCase::class
    factoryOf(::GetWorkerDetailedUseCaseImpl) bind GetWorkerDetailedUseCase::class
    factoryOf(::UpdateWorkerDetailsUseCaseImpl) bind UpdateWorkerDetailsUseCase::class

    factoryOf(::WorkersRepositoryImpl) bind WorkersRepository::class
}
