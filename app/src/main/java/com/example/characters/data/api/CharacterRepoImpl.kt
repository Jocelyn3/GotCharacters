package com.example.characters.data.api

import android.util.Log
import com.example.characters.data.CharactersRepository
import com.example.characters.data.local.CharactersDao
import com.example.characters.data.model.GotCharacter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject


class CharacterRepoImpl @Inject constructor(
    private val api: CharactersApi,
    private val dao: CharactersDao
): CharactersRepository {
    override suspend fun getCharacters(): List<GotCharacter> {
        /*val result = api.getCharacters()

        var listResult : List<GotCharacter> = listOf()

        if (result.isSuccessful) {
            val gson = Gson()
            val collectionType: Type = object : TypeToken<Collection<GotCharacter?>?>() {}.type
            val enums: Collection<GotCharacter> = gson.fromJson(result.body(), collectionType)

            listResult = enums as List<GotCharacter>

            Log.d("GOT", "getCharacters: $listResult")
        }

        return  listResult*/
        val list = dao.getCharacterList()

        if (list.isEmpty()) updateLocalDatabase()

        return dao.getCharacterList()
    }

    override suspend fun updateLocalDatabase() {
        val result = api.getCharacters()

        var listResult : List<GotCharacter> = listOf()

        if (result.isSuccessful) {
            val gson = Gson()
            val collectionType: Type = object : TypeToken<Collection<GotCharacter?>?>() {}.type
            val enums: Collection<GotCharacter> = gson.fromJson(result.body(), collectionType)

            listResult = enums as List<GotCharacter>

            Log.d("GOT", "getCharacters: $listResult")
        }

        dao.insertAll(listResult)

        Log.d("DATA", "updateData: Data updated!")

    }


}