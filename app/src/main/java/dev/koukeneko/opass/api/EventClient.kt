import dev.koukeneko.opass.structs.Event
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*

class EventClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
    private var baseUrl = "https://portal.opass.app/events/"

    suspend fun getEvents(): List<Event> {
        // 发起GET请求并显式地获取响应体作为String
        val response = client.get(baseUrl)
        val responseBody: String = response.body()

        val jsonElements = Json.parseToJsonElement(responseBody).jsonArray

        return jsonElements.mapNotNull { element ->
            val jsonObject = element.jsonObject
            val eventId = jsonObject["event_id"]?.jsonPrimitive?.content ?: return@mapNotNull null
            val displayNameEn = jsonObject["display_name"]?.jsonObject?.get("en")?.jsonPrimitive?.content ?: ""
            val displayNameZh = jsonObject["display_name"]?.jsonObject?.get("zh")?.jsonPrimitive?.content ?: ""
            val logoUrl = jsonObject["logo_url"]?.jsonPrimitive?.content ?: ""

            Event(
                eventId = eventId,
                displayName = mapOf("en" to displayNameEn, "zh" to displayNameZh),
                logoUrl = logoUrl
            )
        }
    }
}
