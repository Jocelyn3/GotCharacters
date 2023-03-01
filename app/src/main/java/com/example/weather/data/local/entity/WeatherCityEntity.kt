package com.example.weather.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.utils.Util
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Util.WEATHER_TABLE)
data class WeatherCityEntity (
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Long,

    @ColumnInfo(name = "temp") val temp: Float,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "pressure") val pressure: Int,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "temp_min") val tempMin: Float,
    @ColumnInfo(name = "temp_max") val tempMax: Float,
    @ColumnInfo(name = "wind_speed") val windSpeed: Float,
    @ColumnInfo(name = "feels_like") val feelsLike: Float,
    @ColumnInfo(name = "description") val description: String?,
) : Parcelable
