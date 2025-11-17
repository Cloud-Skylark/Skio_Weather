package com.example.skioweather

import android.R.attr.data
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.skioweather.api.WeatherModel
import com.example.skioweather.ui.theme.SkioWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        setContent {
            SkioWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        WeatherPage(weatherViewModel)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_9",)
@Composable
fun GreetingPreview() {
    SkioWeatherTheme {
//        WeatherDetails()
    }
}