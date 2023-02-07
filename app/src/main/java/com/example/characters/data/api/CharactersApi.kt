package com.example.characters.data.api

import com.example.characters.utils.Util
import com.google.gson.JsonArray
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET(Util.CHARACTERS_ENDPOINT)
    suspend fun getCharacters() : Response<JsonArray>
}