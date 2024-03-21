package dev.koukeneko.opass.structs

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val eventId: String,
    val displayName: Map<String, String>,
    val logoUrl: String,
    val eventWebsite: String,
    val eventDate: EventDate,
    val publish: PublishPeriod,
    val features: List<Feature>
)

@Serializable
data class EventDate(
    val start: String,
    val end: String
)

@Serializable
data class PublishPeriod(
    val start: String,
    val end: String
)

@Serializable
data class Feature(
    val feature: String,
    val displayText: Map<String, String>,
    val visibleRoles: List<String>? = null,
    val url: String? = null,
    val icon: String? = null,
    val wifi: List<WiFiInfo>? = null
)

@Serializable
data class WiFiInfo(
    val ssid: String,
    val password: String
)
