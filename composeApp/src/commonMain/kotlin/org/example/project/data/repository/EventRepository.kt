package org.example.project.data.repository

import co.touchlab.kermit.Logger
import kotlinx.coroutines.delay
import org.example.project.data.api.ConnpassApiClient

class EventRepository(
    private val connpassApiClient: ConnpassApiClient
) {
    suspend fun hoge(): String {
        delay(timeMillis = 1000)
        val result = connpassApiClient.getSample()
        Logger.d { "HOGE: result is $result" }

//        return "hoge!"
        return result
    }
}