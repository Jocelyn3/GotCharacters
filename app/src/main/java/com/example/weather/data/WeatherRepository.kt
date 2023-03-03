package com.example.weather.data

import com.example.weather.data.local.entity.WeatherCityEntity
import com.example.weather.utils.Resource
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface WeatherRepository {
    suspend fun getAllWeather() : Flow<Resource<List<WeatherCityEntity>>>
    suspend fun getWeatherFromLocalDb(cityName: String) : WeatherCityEntity
    suspend fun insertWeatherInDb(result: Response<JsonObject>?): WeatherCityEntity?
//    suspend fun updateLocalDb(): Flow<Resource<List<WeatherCityEntity>>>
    suspend fun updateLocalDb(): List<WeatherCityEntity>
    suspend fun getQueryResponse(cityName: String): Flow<Resource<Response<JsonObject>>>

}