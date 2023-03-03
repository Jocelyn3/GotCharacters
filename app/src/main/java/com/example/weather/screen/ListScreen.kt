package com.example.weather.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.R
import com.example.weather.data.local.entity.WeatherCityEntity
import com.example.weather.data.viewmodel.WeatherViewModel
import com.example.weather.ui.theme.WeatherTheme
import com.example.weather.utils.Util

@Composable
fun ListScreen(
    navController: NavController,
    context: Context
) {

    val viewModel = hiltViewModel<WeatherViewModel>()
    viewModel.getWeatherList()

    WeatherTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val listState by viewModel.weatherList.collectAsState()
            val list = listState.list
            if (listState.isLoading) {
                Box(modifier = Modifier.padding(9.dp)) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            } else if (listState.error)
                Text(text = "Erreur lors de la récupération des données!")
            else if (listState.list != null)
                WeatherList(list, navController, context)
        }
    }

    if (Util.isNetworkAvailable(context))
        viewModel.updateLocalData()

}


@Composable
fun Fab(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate(Screen.SearchScreen.route)
    }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_add_24),
            contentDescription = "Add button"
        )
    }
}

@Composable
fun WeatherList(list: List<WeatherCityEntity>?, navController: NavController, context: Context) {
    Scaffold(
        floatingActionButton = { Fab(navController) },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {

            if (list != null && list.isNotEmpty())
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(9.dp)
                ) {
                    items(list) { item ->
                        WeatherRow(navController, item)
                    }
                }

            else if (!Util.isNetworkAvailable(context)) NoNetwork()
            else Text(
                text = "Cliquez sur le bouton en bas à droite pour ajouter une ville.",
                fontSize = 17.sp,
                modifier = Modifier.padding(17.dp)
            )
        }
    }
}

@Composable
fun WeatherRow(
    navController: NavController,
    city: WeatherCityEntity) {
    Surface {
        Row(
            Modifier
                .fillMaxSize(1f)
                .padding(9.dp)
                .clickable {
                    navController.navigate(Screen.DetailsScreen.route + "/${city.name}")
                },
            horizontalArrangement = Arrangement.spacedBy(9.dp),
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                city.name?.let { Text(text = it, fontSize = 21.sp) }
                city.description?.let { Text(text = it) }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    WeatherTheme {
//        WeatherList(Util.list)
    }
}