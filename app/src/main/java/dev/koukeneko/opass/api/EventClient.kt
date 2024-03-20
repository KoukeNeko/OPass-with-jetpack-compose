package dev.koukeneko.opass.api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*



class EventClient {
    private val client = HttpClient()
    private var baseUrl = "https://portal.opass.app/events/"
    suspend fun greeting(): String {
        val response = client.get(baseUrl)
        return response.bodyAsText()
    }

}