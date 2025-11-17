package com.example.skioweather

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.skioweather.api.NetworkResponse
import com.example.skioweather.api.WeatherModel

@Composable
fun WeatherPage(viewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val weatherResult = viewModel.weatherResult.observeAsState()

    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = { city = it },
                label = { Text(text = "Search for any location") }
            )
            IconButton(onClick = {
                viewModel.getData(city)
                keyboardController?.hide()
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }

        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> Text(text = result.message)
            NetworkResponse.Loading -> CircularProgressIndicator()
            is NetworkResponse.Success -> WeatherDetails(data = result.data)
            null -> {}
        }
    }
}

@Composable
fun WeatherDetails(data: WeatherModel) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location icon",
                modifier = Modifier.size(40.dp)
            )
            Text(text = data.location.name, fontSize = 30.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country, fontSize = 20.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(28.dp))

        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
            contentDescription = "Condition icon"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Today ${data.current.condition.text}",
            fontSize = 20.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "${data.current.temp_c}°C",
            fontSize = 80.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherCard(key = "Humidity", value = "${data.current.humidity}%")
                WeatherCard(key = "Wind", value = "${data.current.wind_kph}km/h")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherCard(key = "Feels like", value = "${data.current.feelslike_c}°C")
                WeatherCard(key = "UV", value = "${data.current.uv}")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherCard(key = "Pressure", value = "${data.current.pressure_mb}mb")
                WeatherCard(key = "Precip", value = "${data.current.precip_mm}mm")
            }
        }
    }
}

@Composable
fun WeatherCard(key: String, value: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(80.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = value, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(text = key, fontSize = 14.sp, color = Color.Gray)
        }
    }
}
