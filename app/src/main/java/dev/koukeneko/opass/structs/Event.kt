package dev.koukeneko.opass.structs

data class Event(
    val eventId: String,
    val displayName: Map<String, String>,
    val logoUrl: String,
)
