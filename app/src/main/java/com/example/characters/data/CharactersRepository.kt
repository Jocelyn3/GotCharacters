package com.example.characters.data

import com.example.characters.data.model.GotCharacter

interface CharactersRepository {
    suspend fun getCharacters() : List<GotCharacter>
}