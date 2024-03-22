package dev.koukeneko.opass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.koukeneko.opass.api.EventClient
import dev.koukeneko.opass.structs.Event
import dev.koukeneko.opass.structs.EventListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class EventUiState(
    val currentEvent: Event? = null,
    val eventList: List<EventListItem> = emptyList(),
    val currentEventId: String? = null,
)

class EventViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState.asStateFlow()

    init {
        // Launch a coroutine in the ViewModel's scope
        viewModelScope.launch {
            // Fetch event list data and set the first event as current event
            getCurrentEvent(getEventList().first().eventId)?.let {
                // set current event id
                setCurrentEventId(it.eventId)
            }
        }
    }

    // Handle event logic

    // Get Current Event
    suspend fun getCurrentEvent(eventId: String): Event? {
        // Fetch event data
        val event = EventClient().getEvent(eventId)
        // Update UI state
        _uiState.value = _uiState.value.copy(currentEvent = event, currentEventId = event.eventId)
        return event
    }

    // Get Event List
    suspend fun getEventList(): List<EventListItem> {
        // Fetch event list data
        val eventList = EventClient().getEventList()
        // Update UI state
        _uiState.value = _uiState.value.copy(eventList = eventList)
        return eventList
    }

    fun setCurrentEventId(eventId: String) {
        _uiState.value = _uiState.value.copy(currentEventId = eventId)
        // Fetch current event data when event id is set
        viewModelScope.launch {
            getCurrentEvent(eventId)
        }
    }

}