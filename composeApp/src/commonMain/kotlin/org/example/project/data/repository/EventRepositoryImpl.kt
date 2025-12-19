package org.example.project.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.api.ConnpassApiClient.ConnpassQueryFilter
import org.example.project.data.db.EventDao
import org.example.project.data.db.EventDto

class EventRepositoryImpl(
    private val connpassApiClient: ConnpassApiClient,
    private val eventDao: EventDao,
): EventRepository {
    override val events = eventDao.getAll()
    override val favoriteEvents = eventDao.getFavoriteEvents()
    override val onlineEvents: Flow<List<EventDto>> = eventDao.getOnlineEvents()
    override val newestEvents: Flow<List<EventDto>> = eventDao.getNewestEvents()
    override val upcomingEvents: Flow<List<EventDto>> = eventDao.getUpcomingEvents()


    override suspend fun refresh(
        start: Int,
        page: Int,
        filter: ConnpassQueryFilter,
    ): Result<Unit> {
        return handleApiResult {
            val result = connpassApiClient.getEvents(
                start = start,
                count = page,
                filter = filter,
            )
            val events = result.events.map { event ->
                EventDto(
                    id = event.id,
                    title = event.title,
                    url = event.url,
                    startedAt = event.startedAt,
                    place = event.place,
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
                    url = event.url,
                    startAt = event.startedAt,
                    place = event.place,
                )
            }
        }
    }
}

