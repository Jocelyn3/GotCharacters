package com.example.weather.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.data.viewmodel.WeatherViewModel

@Composable
fun DetailsScreen(
    cityName: String?) {
    Column(
        modifier = Modifier.fillMaxSize().padding(11.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        val viewModel = hiltViewModel<WeatherViewModel>()

        cityName?.let {
            viewModel.getWeatherCity(cityName)

            val cityState by viewModel.weatherCity.collectAsState()
            val city = cityState.weatherCity

            city?.let {

                city.name?.let { Text(text = it, fontSize = 21.sp, modifier = Modifier.padding(bottom = 7.dp)) }

                city.description?.let { Text(text = "Description: $it", fontSize = 17.sp) }

                Text(text = "Resenti: ${city.feelsLike}", fontSize = 17.sp)
                Text(text = "Température: ${city.temp} °C", fontSize = 17.sp)
                Text(text = "Température Minimale: ${city.tempMin} °C", fontSize = 17.sp)
                Text(text = "Température Maximale: ${city.tempMax} °C", fontSize = 17.sp)

                Text(text = "Vitesse du vent: ${city.windSpeed}", fontSize = 17.sp)
                Text(text = "Pression: ${city.pressure}", fontSize = 17.sp)
                Text(text = "Humidité: ${city.humidity}", fontSize = 17.sp)


            }
        }



    }
}