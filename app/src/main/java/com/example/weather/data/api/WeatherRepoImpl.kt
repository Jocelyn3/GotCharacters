package com.example.weather.data.api

import android.util.Log
import com.example.weather.data.WeatherRepository
import com.example.weather.data.local.WeatherDao
import com.example.weather.data.local.entity.WeatherCityEntity
import com.example.weather.data.model.WeatherCity
import com.example.weather.utils.Resource
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject


class WeatherRepoImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherDao
): WeatherRepository {
    override suspend fun getAllWeather() = flow {
        try {
            emit(Resource.Loading)
            val list = dao.getWeatherCityList()
            emit(Resource.Success(list))
        }catch (e: java.lang.Exception) {
            emit(Resource.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertWeatherInDb(result: Response<JsonObject>?): WeatherCityEntity? {
        if (result != null && result.isSuccessful) {

            Log.d("RESULT", "updateLocalDatabase: ${result.body()}")
            val gson = Gson()
            val entity = gson.fromJson(result.body(), WeatherCity::class.java)

            val cityEntity = WeatherCityEntity(
                id = entity.id,
                name = entity.name,
                temp = entity.main.temp,
                windSpeed = entity.wind.speed,
                tempMin = entity.main.tempMin,
                tempMax = entity.main.tempMax,
                pressure = entity.main.pressure,
                humidity = entity.main.humidity,
                feelsLike = entity.main.feelsLike,
                description = entity.weathers[0].description,
            )

            dao.insert(cityEntity)

            return cityEntity
        }

        return null
    }

    override suspend fun getQueryResponse(cityName: String) = flow{
        try {
            emit(Resource.Loading)
            val result = api.getWeather(cityName)
            emit(Resource.Success(result))
        }catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun delete(cityName: String) {
        dao.delete(cityName)
    }

    override suspend fun updateLocalDb(): List<WeatherCityEntity> {

            val list = dao.getWeatherCityList()
            val finalList = mutableListOf<WeatherCityEntity>()

            if (list.isNotEmpty()) {
                for (weather in list) {
                    val city = insertWeatherInDb(weather.name?.let { api.getWeather(it) })
                    city?.let { finalList.add(it) }
                }
            }

        return finalList
    }

    override suspend fun getWeatherFromLocalDb(cityName: String): WeatherCityEntity {
        return dao.getWeatherCity(cityName)
    }
}