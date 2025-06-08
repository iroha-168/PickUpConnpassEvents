package org.example.project.data.repository

import co.touchlab.kermit.Logger
import kotlinx.coroutines.delay
import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.db.EventDao
import org.example.project.data.db.EventDto

class EventRepository(
    private val connpassApiClient: ConnpassApiClient,
    private val eventDao: EventDao,
) {
    val events = eventDao.getAll()

    suspend fun hoge(): String {
        delay(timeMillis = 1000)
        val result = connpassApiClient.getSample()
        return result
    }

    suspend fun upsertEvents(events: List<EventDto>) {
        events.forEach { event ->
            if (eventDao.insertEvent(event) == -1L) {
                eventDao.updateEvent(event)
            }
        }
    }
}