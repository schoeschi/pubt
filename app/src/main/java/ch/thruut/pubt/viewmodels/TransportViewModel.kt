package ch.thruut.pubt.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.thruut.pubt.data.repository.TransportRepository
import ch.thruut.pubt.data.response.Connection
import ch.thruut.pubt.data.response.Station
import kotlinx.coroutines.launch

class TransportViewModel(private val repository: TransportRepository = TransportRepository()) :
    ViewModel() {
    var stations by mutableStateOf<List<Station>>(emptyList())
        private set

    var journey by mutableStateOf<List<Connection>>(emptyList())
        private set

    fun searchStation(query: String) = viewModelScope.launch {
        if (query.isBlank()) {
            stations = emptyList()
            return@launch
        }

        try {
            stations = repository.getLocations(query).stations
        } catch (e: Exception) {
            e.printStackTrace()
            stations = emptyList()
        }
    }

    fun findJourney(from: Station, to: Station) = viewModelScope.launch {
        try {
            val response = repository.getJourney(from, to)
            journey = response.connections
        } catch (e: Exception) {
            e.printStackTrace()
            journey = emptyList()
        }
    }
}