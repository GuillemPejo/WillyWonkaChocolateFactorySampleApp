package com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.responsemodels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultData(
    @SerialName("current")
    val current: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("results")
    val results: List<OompaLoompaData>,

    )