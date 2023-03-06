package com.example.weather

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weather.data.local.WeatherDao
import com.example.weather.data.local.WeatherRoomDatabase
import com.example.weather.data.local.entity.WeatherCityEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDbTest {
    private lateinit var roomDb: WeatherRoomDatabase
    private lateinit var roomDao: WeatherDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        roomDb = Room.inMemoryDatabaseBuilder(context, WeatherRoomDatabase::class.java).build()
        roomDao = roomDb.WeatherDao()
    }

    @After
    fun closeDb() {
        roomDb.close()
    }

    @Test
    fun insertAndGetListSucceeded() {
        val list: List<WeatherCityEntity> = listOf(
            WeatherCityEntity(7924, 1.2f, "Nice", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy"),
            WeatherCityEntity(1798, 1.2f, "Lyon", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy"),
            WeatherCityEntity(1258, 1.2f, "Paris", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy"),
            WeatherCityEntity(4578, 1.2f, "Nantes", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy"),
            WeatherCityEntity(1984, 1.2f, "Bordeaux", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy"),
        )

        runBlocking {
            roomDao.insertAll(list)
            val retrievedList = roomDao.getWeatherCityList()

            TestCase.assertEquals(retrievedList.isNotEmpty(), true)
            TestCase.assertEquals(retrievedList.size == 5, true)
        }
    }

    @Test
    fun insertAndGetItemSucceeded() {
        val weather =
            WeatherCityEntity(7924, 1.2f, "Nice", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy")


        runBlocking {
            roomDao.insert(weather)
            val item = roomDao.getWeatherCity("Nice")

            TestCase.assertEquals(item.name, "Nice")
        }
    }

    @Test
    fun delete() {
        val list: List<WeatherCityEntity> = listOf(
            WeatherCityEntity(7924, 1.2f, "Nice", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy"),
            WeatherCityEntity(1798, 1.2f, "Lyon", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy"),
            WeatherCityEntity(1984, 1.2f, "Bordeaux", 100, 115, -1.5f, 3.7f, 30f, -3f, "Windy"),
        )

        runBlocking {
            roomDao.insertAll(list)
            var retrievedList = roomDao.getWeatherCityList()

            TestCase.assertEquals(retrievedList.isNotEmpty(), true)
            TestCase.assertEquals(retrievedList.size == 3, true)

            roomDao.deleteAll()
            retrievedList = roomDao.getWeatherCityList()
            TestCase.assertEquals(retrievedList.isEmpty(), true)

            roomDao.insertAll(list)
            roomDao.delete("Lyon")
            retrievedList = roomDao.getWeatherCityList()
            TestCase.assertEquals(retrievedList.isNotEmpty(), true)
            TestCase.assertEquals(retrievedList.size == 2, true)
            TestCase.assertEquals(retrievedList[0].name != "Lyon", true)
            TestCase.assertEquals(retrievedList[1].name != "Lyon", true)

        }
    }
}