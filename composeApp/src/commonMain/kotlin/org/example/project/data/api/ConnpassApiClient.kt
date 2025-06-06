package org.example.project.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import org.example.project.data.entity.EventResponse

class ConnpassApiClient {
    private val client = HttpClient()

    suspend fun getEvents(): EventResponse {
        return client.get(ENDPOINT_EVENTS){
            headers {
                // TODO: キーは先方から提示されるかも
                append(HttpHeaders.Authorization, CONNPASS_API_KEY)
            }
        }.body()
    }

    suspend fun getSample(): String {
        return client.get(ENDPOINT_SAMPLE).body()
    }


    companion object {
        // TODO: API_KEYを第三者に漏洩しないように管理したい
        private const val CONNPASS_API_KEY = ""
        private const val ENDPOINT_EVENTS = "/api/v1/event/"
        private const val ENDPOINT_SAMPLE = "https://ktor.io/"
    }
}