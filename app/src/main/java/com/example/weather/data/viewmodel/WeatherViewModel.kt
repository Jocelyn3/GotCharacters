package com.example.weather.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.WeatherRepository
import com.example.weather.utils.Resource
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private var _weatherList = MutableStateFlow(ListUiState(listOf()))
    var weatherList: StateFlow<ListUiState> = _weatherList

    private var _weatherCity = MutableStateFlow(WeatherState())
    var weatherCity: MutableStateFlow<WeatherState> = _weatherCity

    private var _queryResponse = MutableStateFlow(QueryResponseState())
    var queryResponse: MutableStateFlow<QueryResponseState> = _queryResponse



    fun insert(result: Response<JsonObject>?) {
        viewModelScope.launch {
            repository.insertWeatherInDb(result)
        }
    }
    fun getWeatherList() {
        viewModelScope.launch {
            repository.getAllWeather().collect{
                when(it) {
                    is Resource.Success -> _weatherList.value = ListUiState(it.data)
                    is Resource.Loading -> _weatherList.value = ListUiState(
                        list = null,
                        isLoading = true,
                    )
                    is Resource.Failure -> _weatherList.value = ListUiState(
                        list = null,
                        error = true,
                    )
                }
            }
        }
    }

    fun getWeatherCity(cityName: String) {
        viewModelScope.launch {
            _weatherCity.value = WeatherState(repository.getWeatherFromLocalDb(cityName))
        }
    }

    fun getQueryResponse(cityName: String) {
        viewModelScope.launch {
            repository.getQueryResponse(cityName).collect {
                when(it) {
                    is Resource.Success -> _queryResponse.value = QueryResponseState(it.data)
                    is Resource.Loading ->
                        _queryResponse.value = QueryResponseState(
                            response = null,
                            isLoading = true
                        )
                    is Resource.Failure ->
                        _queryResponse.value = QueryResponseState(
                            response = null,
                            error = true
                        )
                }
            }
        }
    }

    fun updateLocalData() {
        viewModelScope.launch {
            repository.updateLocalDb().collect{
                when(it) {
                    is Resource.Success -> _weatherList.value = ListUiState(it.data)
                    is Resource.Loading ->
                        _weatherList.value = ListUiState(
                            list = null,
                            isLoading = true
                        )
                    is Resource.Failure ->
                        _weatherList.value = ListUiState(
                            list = null,
                            error = true
                        )
                }
            }
        }
    }
}
