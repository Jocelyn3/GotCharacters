package com.example.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.local.entity.WeatherCityEntity
import com.example.weather.utils.Util

@Dao
interface WeatherDao {
    @Query("SELECT * FROM ${Util.WEATHER_TABLE}")
    suspend fun getWeatherCityList(): List<WeatherCityEntity>

    @Query("SELECT * FROM ${Util.WEATHER_TABLE} WHERE name = :name")
    suspend fun getWeatherCity(name: String): WeatherCityEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherCity: WeatherCityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weatherCityList: List<WeatherCityEntity>)

    @Query("DELETE FROM ${Util.WEATHER_TABLE}")
    suspend fun deleteAll()

    @Query("DELETE FROM ${Util.WEATHER_TABLE} WHERE name = :name")
    suspend fun delete(name: String)
}