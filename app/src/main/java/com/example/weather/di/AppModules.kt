package com.example.weather.di

import android.content.Context
import com.example.weather.data.WeatherRepository
import com.example.weather.data.api.WeatherRepoImpl
import com.example.weather.data.api.WeatherApi
import com.example.weather.data.local.WeatherDao
import com.example.weather.data.local.WeatherRoomDatabase
import com.example.weather.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideRetrofit(
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Util.BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(
        weatherRetrofit: Retrofit
    ): WeatherApi = weatherRetrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherLocalDatabase(
        @ApplicationContext context: Context
    ): WeatherRoomDatabase =
        WeatherRoomDatabase
            .getDatabase(
                context,
                CoroutineScope(SupervisorJob()
            )
        )

    @Provides
    fun provideWeatherDao(
        weatherRoomDatabase: WeatherRoomDatabase
    ): WeatherDao = weatherRoomDatabase.WeatherDao()

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi,
        dao: WeatherDao
    ): WeatherRepository = WeatherRepoImpl(api, dao)

}
