package com.example.weather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weather.data.local.entity.WeatherCityEntity
import com.example.weather.utils.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [WeatherCityEntity::class], version = 1, exportSchema = false)
abstract class WeatherRoomDatabase : RoomDatabase() {

    abstract fun WeatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WeatherRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    Util.WEATHER_DATABASE
                )
                    .addCallback(WeatherRoomDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }

    private class WeatherRoomDatabaseCallback(private val scope: CoroutineScope) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {

                }
            }
        }

    }
}