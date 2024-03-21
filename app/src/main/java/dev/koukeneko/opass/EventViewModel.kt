package dev.koukeneko.opass

import androidx.lifecycle.ViewModel
import dev.koukeneko.opass.structs.EventListItem

class EventViewModel: ViewModel() {

    var events: List<EventListItem> = emptyList()
    var eventId = ""

}