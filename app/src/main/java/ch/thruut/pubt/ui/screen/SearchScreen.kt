package ch.thruut.pubt.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ch.thruut.pubt.viewmodels.TransportViewModel

@Composable
fun SearchScreen(viewModel: TransportViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = uiState.searchQuery,
            label = { Text(text = "Search station") },
            onValueChange = { viewModel.onSearchQueryChange(it) },
            singleLine = true
        )

        Button(
            onClick = { viewModel.searchStation() },
            enabled = uiState.searchQuery.isNotBlank()
        ) {
            Text("Search")
        }

        uiState.stations.forEach { station ->
            Text(text = station.name)
        }
    }
}