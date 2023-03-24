package com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.utils

import android.accounts.NetworkErrorException
import com.guillem.sample_app_willy_wonka_chocolate_factory.R
import com.guillem.sample_app_willy_wonka_chocolate_factory.core.utils.MyResult
import retrofit2.HttpException
import timber.log.Timber

const val API_ERROR_TAG = "ApiError"

enum class GenericApiError { NoInternet, Network, Server, Http, Unknown }

fun GenericApiError.toUi() =
    when(this){
        GenericApiError.NoInternet -> R.string.api_error_no_internet
        GenericApiError.Network -> R.string.api_error_network
        GenericApiError.Server -> R.string.api_error_server
        GenericApiError.Http -> R.string.api_error_http
        GenericApiError.Unknown -> R.string.api_error_unknown
    }

object ApiErrorHandling {
    fun handleError(e: Throwable) = when (e) {
        is NoConnectivityException -> MyResult.Err(GenericApiError.NoInternet)
            .also { Timber.tag(API_ERROR_TAG).d(e) }
        is NetworkErrorException -> MyResult.Err(GenericApiError.Network)
            .also { Timber.tag(API_ERROR_TAG).d(e) }
        is ClassCastException -> MyResult.Err(GenericApiError.Server)
            .also { Timber.tag(API_ERROR_TAG).d(e) }
        is HttpException -> MyResult.Err(GenericApiError.Http)
            .also { Timber.tag(API_ERROR_TAG).d(e) }
        else -> MyResult.Err(GenericApiError.Unknown).also { Timber.tag(API_ERROR_TAG).d(e) }
    }
}