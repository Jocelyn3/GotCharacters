package com.example.characters.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characters.data.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharactersRepository
): ViewModel() {

    private var _characterList = MutableStateFlow(ListUiState(listOf()))
    var characterList: StateFlow<ListUiState> = _characterList



    fun getCharacterList() {
        viewModelScope.launch {
            _characterList.value = ListUiState(repository.getCharacters())
        }
    }

    fun updateLocalData() {
        viewModelScope.launch {
            repository.updateLocalDatabase()
        }
    }
}
