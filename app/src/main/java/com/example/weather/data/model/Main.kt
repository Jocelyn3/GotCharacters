package com.example.weather.data.model

import com.google.gson.annotations.SerializedName

data class Main (
    val temp: Float,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
    @SerializedName("feels_like") val feelsLike: Float
)
