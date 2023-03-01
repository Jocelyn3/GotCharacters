package com.example.weather.data.model

import com.google.gson.annotations.SerializedName

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    @SerializedName("icon") val iconId: String
)