package com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote

import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.responsemodels.OompaLoompaDetailedData
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.responsemodels.ResultData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val OOMPA_LOOMPAS_ENDPOINT_PATH = "oompa-loompas/"

interface ApiEndpoints {
    @GET(OOMPA_LOOMPAS_ENDPOINT_PATH)
    suspend fun getWorkers(
        @Query("page") page: Int,
    ): ResultData

    @GET("$OOMPA_LOOMPAS_ENDPOINT_PATH{workerId}")
    suspend fun getWorkerDetailsById(
        @Path("workerId") workerId: Int
    ): OompaLoompaDetailedData
}
