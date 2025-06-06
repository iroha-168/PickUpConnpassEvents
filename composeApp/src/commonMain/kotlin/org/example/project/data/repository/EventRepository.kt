package org.example.project.data.repository

import kotlinx.coroutines.delay

class EventRepository {
    suspend fun hoge(): String {
        delay(timeMillis = 1000)
        return "hoge!"
    }
}