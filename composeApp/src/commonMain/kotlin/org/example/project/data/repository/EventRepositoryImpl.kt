package org.example.project.data.repository

import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.db.EventDao
import org.example.project.data.db.EventDto

class EventRepositoryImpl(
    private val connpassApiClient: ConnpassApiClient,
    private val eventDao: EventDao,
): EventRepository {
    override val events = eventDao.getAll()
    override val favoriteEvents = eventDao.getFavoriteEvents()

    override suspend fun refresh(
        start: Int,
        page: Int,
    ): Result<Unit> {
        return handleApiResult {
            val result = connpassApiClient.getEvents(
                start = start,
                count = page,
            )
            val events = result.events.map {
                EventDto(
                    id = it.id,
                    title = it.title,
                    startedAt = it.startedAt,
                    place = it.place,
                    isFavorite = false
                )
            }
            upsertEvents(events)
            Result.success(Unit)
        }
    }

    override suspend fun updateFavorite(
        id: Long,
        isFavorite: Boolean,
    ) {
        eventDao.updateFavorite(
            id = id,
            isFavorite = isFavorite,
        )
    }

    private suspend fun upsertEvents(events: List<EventDto>) {
        events.forEach { event ->
            if (eventDao.insertEvent(event) == -1L) {
                eventDao.updateEvent(
                    id = event.id,
                    title = event.title,
                    startAt = event.startedAt,
                    place = event.place,
                )
            }
        }
    }
}

