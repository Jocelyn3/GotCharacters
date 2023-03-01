package com.example.weather.data.viewmodel

import com.example.weather.data.local.entity.WeatherCityEntity
import com.google.gson.JsonObject
import retrofit2.Response

class ListUiState(
    val list: List<WeatherCityEntity>?,
    val isLoading: Boolean = false,
    val error: Boolean = false
)

class WeatherState(
    val weatherCity: WeatherCityEntity? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
)

class QueryResponseState(
    val response: Response<JsonObject>? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
)