package com.example.weather.data.model

import com.google.gson.annotations.SerializedName

data class WeatherCity (
    val id: Long,
    val main: Main,
    val wind: Wind,
    val name: String,
    @SerializedName("weather") val weathers: List<Weather>
) {
    override fun toString(): String {
        return "\nId: $id; \n" +
                "name: $name; \n" +
               "$weathers; \n" +
               "$main; \n" +
               "$wind; \n"
    }
}