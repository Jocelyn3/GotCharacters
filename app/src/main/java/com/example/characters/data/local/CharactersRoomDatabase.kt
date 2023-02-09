package com.example.characters.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.characters.data.model.GotCharacter
import com.example.characters.utils.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [GotCharacter::class], version = 1, exportSchema = false)
abstract class CharactersRoomDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    companion object {
        @Volatile
        private var INSTANCE: CharactersRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CharactersRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersRoomDatabase::class.java,
                    Util.GOT_CHARACTERS_DATABASE
                )
                    .addCallback(CharactersRoomDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

//                return instance
                instance
            }
        }
    }

    private class CharactersRoomDatabaseCallback(private val scope: CoroutineScope) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val charactersDao = database.charactersDao()

                    // Delete all content here.
                    charactersDao.deleteAll()

                }
            }
        }

    }
}