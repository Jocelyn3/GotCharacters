package com.example.weather.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.data.viewmodel.WeatherViewModel
import com.example.weather.utils.Util

@Composable
fun SearchScreen(
    navController: NavController,
    context: Context
) {
    val viewModel = hiltViewModel<WeatherViewModel>()
    val cityEntered = remember {
        mutableStateOf("")
    }
    val error = remember {
        mutableStateOf(false)
    }
    val isLoading = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 51.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = cityEntered.value,
            onValueChange = {
                cityEntered.value = it
                viewModel.getQueryResponse(cityEntered.value) },
            label = { Text(text = "Entrez une ville") },
        )

        val result by viewModel.queryResponse.collectAsState()
        isLoading.value = result.isLoading
        error.value = result.error

        val response = result.response

        Button(onClick = {
                if (response != null) {
                    viewModel.insert(response)
                    Log.d("BODY: ", "SearchScreen: ${response.code()}")
                    Log.d("BODY: ", "SearchScreen: ${response.body()}")

                    if (response.code() == 200)
                        navController.popBackStack()
                }
            },
            modifier = Modifier
                .padding(top = 7.dp)
                .padding(horizontal = 95.dp),
            enabled = !isLoading.value
        )
        {
            Text(text = "Valider")
        }

        if (!Util.isNetworkAvailable(context))
            Text(
                text = "Assurez-vous d'avoir accès à une bonne connexion Internet",
                color = Color.Red,
                modifier = Modifier.padding(top = 17.dp),
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic
            )
        else {
            if (response != null && response.code() != 200)
                Text(text = "Ville invalide!", color = Color.Red, fontStyle = FontStyle.Italic)
            else Text(text = "")
        }

    }
}