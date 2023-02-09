package com.example.characters.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.characters.data.model.GotCharacter
import com.example.characters.utils.Util

@Dao
interface CharactersDao {
    @Query("SELECT * FROM ${Util.CHARACTER_TABLE} ORDER BY title ASC")
    suspend fun getCharacterList(): List<GotCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gotCharacter: GotCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characterList: List<GotCharacter>)

    @Query("DELETE FROM ${Util.CHARACTER_TABLE}")
    suspend fun deleteAll()
}