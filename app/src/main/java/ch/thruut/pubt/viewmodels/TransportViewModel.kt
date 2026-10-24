package ch.thruut.pubt.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.thruut.pubt.data.Station
import ch.thruut.pubt.data.TransportRepository
import kotlinx.coroutines.launch

class TransportViewModel(private val repository: TransportRepository = TransportRepository()) :
    ViewModel() {
    var stations by mutableStateOf<List<Station>>(emptyList())
        private set

    fun search(query: String) = viewModelScope.launch {
        stations = repository.getLocations(query).stations
    }
}