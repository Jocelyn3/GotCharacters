package com.example.weather.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                val context = this
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar { Text(text = "Weather", fontSize = 23.sp, fontWeight = FontWeight.Medium) }
                    }
                ) { contentPadding ->
                        Box(modifier = Modifier.padding(contentPadding)) {
                            Navigation(context)
                        }
                    }


            }
        }

    }


}

@Composable
fun NoNetwork() {
    Text(text = "Network Service not available", fontSize = 21.sp)
}

@Preview(showBackground = true)
@Composable
fun FragmentPreview() {
    WeatherTheme {
    }
}