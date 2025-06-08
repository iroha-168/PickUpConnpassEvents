package org.example.project.data.repository

import kotlinx.coroutines.delay
import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.db.EventDao
import org.example.project.data.db.EventDto
import org.example.project.data.entity.Event

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

    suspend fun refresh() {
        val result = connpassApiClient.getEvents()
        val events = filterEvents(result.events)
        upsertEvents(events)
    }

    private suspend fun upsertEvents(events: List<EventDto>) {
        events.forEach { event ->
            if (eventDao.insertEvent(event) == -1L) {
                eventDao.updateEvent(event)
            }
        }
    }

    private fun filterEvents(events: List<Event>): List<EventDto> {
        return events.filter {
            it.title.contains(BIG_ANDROID) ||
            it.title.contains(SMALL_ANDROID) ||
            it.title.contains(BIG_KOTLIN) ||
            it.title.contains(SMALL_KOTLIN) ||
            it.title.contains(BIG_IOS) ||
            it.title.contains(SMALL_IOS) ||
            it.title.contains(BIG_SWIFT) ||
            it.title.contains(SMALL_SWIFT)
        }.map {
            EventDto(
                id = it.id,
                title = it.title,
                startedAt = it.startedAt,
                place = it.place,
                isFavorite = false
            )
        }
    }

    companion object {
        private const val BIG_ANDROID = "Android"
        private const val SMALL_ANDROID = "android"
        private const val BIG_KOTLIN = "Kotlin"
        private const val SMALL_KOTLIN = "kotlin"

        private const val BIG_IOS = "iOS"
        private const val SMALL_IOS = "ios"
        private const val BIG_SWIFT = "Swift"
        private const val SMALL_SWIFT = "swift"
    }
}

