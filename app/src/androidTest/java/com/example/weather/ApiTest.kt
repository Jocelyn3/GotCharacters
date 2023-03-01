package com.example.weather

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weather.data.api.WeatherApi
import com.example.weather.utils.Util
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//@RunWith(AndroidJUnit4ClassRunner::class)
@RunWith(AndroidJUnit4::class)
class ApiTest : TestCase(){
    private val api = Api.provide()

    @Test
    fun getWeatherCitySucceeded() {
        val test = runBlocking {
            api.getWeather("Paris")
        }

        assertEquals(test.isSuccessful, true)
        assertEquals(test.body()?.get("name")?.asString, "Paris")
    }

}

object Api  {
    fun provide(): WeatherApi = provideRetrofit().create()

    private fun provideRetrofit(
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
}


