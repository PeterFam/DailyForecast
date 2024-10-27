package com.peterfam.dailyforecast.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.peterfam.dailyforecast.data.remote.response.CitiesItem

@Composable
fun AppBarWithDropdown(
    cities: List<CitiesItem>,
    selectedCity: CitiesItem?,
    onCitySelected: (CitiesItem) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(text = selectedCity?.cityNameEn ?: "Select City")
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Select City")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onCitySelected(city)
                        },
                        text = { Text(text = city.cityNameEn ?: "Unknown City") }
                    )
                }
            }
        }
    )
}