package ch.thruut.pubt.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ch.thruut.pubt.data.response.Station
import ch.thruut.pubt.viewmodels.TransportViewModel

@Composable
fun SearchScreen() {
    val viewModel: TransportViewModel = viewModel()

    var fromText by remember { mutableStateOf("") }
    var toText by remember { mutableStateOf("") }

    var selectedFrom by remember { mutableStateOf<Station?>(null) }
    var selectedTo by remember { mutableStateOf<Station?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = fromText,
            onValueChange = { newText ->
                fromText = newText
                selectedFrom = null
                viewModel.searchStation(newText)
            },
            label = { Text("Enter from") },
            singleLine = true
        )

        if (selectedFrom == null && fromText.isNotEmpty()) {
            viewModel.stations.forEach { station ->
                Text(
                    text = station.name,
                    modifier = Modifier
                        .clickable {
                            selectedFrom = station
                            fromText = station.name
                        }
                        .padding(8.dp)
                )
            }
        }

        OutlinedTextField(
            value = toText,
            onValueChange = { newText ->
                toText = newText
                selectedTo = null
                viewModel.searchStation(newText)
            },
            label = { Text("Enter to") },
            singleLine = true
        )

        if (selectedTo == null && toText.isNotEmpty()) {
            viewModel.stations.forEach { station ->
                Text(
                    text = station.name,
                    modifier = Modifier
                        .clickable {
                            selectedTo = station
                            toText = station.name
                        }
                        .padding(8.dp)
                )
            }
        }

        Button(
            onClick = {
                if (selectedFrom != null && selectedTo != null) {
                    viewModel.findJourney(selectedFrom!!, selectedTo!!)
                }
            },
            enabled = selectedFrom != null && selectedTo != null,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Search")
        }

        viewModel.journey.forEach { connection ->
            Text("Depart from platform ${connection.from.platform} in ${connection.from.station.name}")
            Text("Arrive in ${connection.to.station.name} on platform ${connection.to.platform}")
        }
    }
}