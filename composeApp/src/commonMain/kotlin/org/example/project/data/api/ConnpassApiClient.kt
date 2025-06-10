package org.example.project.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.BuildKonfig
import org.example.project.data.entity.EventResponse

class ConnpassApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(json = Json{
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun getEvents(): EventResponse {
        return client.get(ENDPOINT_EVENTS){
            headers {
                append(X_API_KEY, BuildKonfig.API_KEY)
                append(HttpHeaders.ContentType, "application/json")
            }
            url {
                parameters.append("keyword_or", "android")
                parameters.append("keyword_or", "kotlin")
                parameters.append("keyword_or", "ios")
                parameters.append("keyword_or", "swift")
                parameters.append("count", "20")
                parameters.append("order", "2")
            }
        }.body()
    }

    companion object {
        private const val X_API_KEY = "X-API-Key"
        private const val ENDPOINT_EVENTS = "https://connpass.com/api/v2/events/"
        private const val ENDPOINT_SAMPLE = "https://ktor.io/"
    }
}
