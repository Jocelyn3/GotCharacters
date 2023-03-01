package com.example.weather.data.api

import com.example.weather.utils.Util
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Util.WEATHER_ENDPOINT)
    suspend fun getWeather(
        @Query("q") city: String
    ) : Response<JsonObject>
}