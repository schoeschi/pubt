package ch.thruut.pubt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.thruut.pubt.data.repository.TransportRepository
import ch.thruut.pubt.data.response.Connection
import ch.thruut.pubt.data.response.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TransportUiState(
    val stations: List<Station> = emptyList(),
    val journey: List<Connection> = emptyList(),
    val selectedFrom: Station? = null,
    val selectedTo: Station? = null,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
)

class TransportViewModel(
    private val repository: TransportRepository = TransportRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransportUiState())
    val uiState: StateFlow<TransportUiState> = _uiState.asStateFlow()

    fun searchStation() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val query = _uiState.value.searchQuery
        if (query.isBlank()) {
            _uiState.update { it.copy(stations = emptyList()) }
            return@launch
        }
        val stations =
            runCatching { repository.getLocations(query).stations }.getOrDefault(emptyList())
        _uiState.update { it.copy(stations = stations, isLoading = false) }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }
}