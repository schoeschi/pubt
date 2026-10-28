package ch.thruut.pubt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.thruut.pubt.data.repository.TransportRepository
import ch.thruut.pubt.data.response.Connection
import ch.thruut.pubt.data.response.Station
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

data class TransportUiState(
    val stations: List<Station> = emptyList(),
    val journey: List<Connection> = emptyList(),
    val selectedOrigin: Station? = null,
    val selectedDestination: Station? = null,
    val isLoading: Boolean = false,
    val originQuery: String = "",
    val destinationQuery: String = "",
    val activeField: SearchField = SearchField.NONE,
)

enum class SearchField {
    ORIGIN, DESTINATION, NONE
}

class TransportViewModel(
    private val repository: TransportRepository = TransportRepository()
) : ViewModel() {
    private var searchJob: Job? = null
    private val _uiState = MutableStateFlow(TransportUiState())

    val uiState: StateFlow<TransportUiState> = _uiState.asStateFlow()

    fun searchStation(query: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        if (query.isBlank()) {
            _uiState.update { it.copy(stations = emptyList(), isLoading = false) }
            return@launch
        }
        val stations =
            runCatching { repository.getLocations(query).stations }.getOrDefault(emptyList())
        _uiState.update { it.copy(stations = stations, isLoading = false) }
    }

    fun onOriginQueryChange(query: String) {
        _uiState.update { it.copy(originQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500.milliseconds)
            searchStation(query)
        }
    }

    fun onDestinationQueryChange(query: String) {
        _uiState.update { it.copy(destinationQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500.milliseconds)
            searchStation(query)
        }
    }

    fun onFieldFocused(field: SearchField) {
        _uiState.update { it.copy(activeField = field) }
        val query =
            if (field == SearchField.ORIGIN) _uiState.value.originQuery else _uiState.value.destinationQuery
        searchStation(query)
    }

    fun onStationSelect(station: Station) {
        if (_uiState.value.activeField == SearchField.ORIGIN) {
            _uiState.update {
                it.copy(
                    selectedOrigin = station,
                    originQuery = station.name,
                    stations = emptyList()
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    selectedDestination = station,
                    destinationQuery = station.name,
                    stations = emptyList()
                )
            }
        }
    }
}
