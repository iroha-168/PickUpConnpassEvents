package org.example.project.data.repository

import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.db.EventDao
import org.example.project.data.db.EventDto
import kotlin.jvm.JvmStatic

class EventRepository(
    private val connpassApiClient: ConnpassApiClient,
    private val eventDao: EventDao,
) {
    val events = eventDao.getAll()
    val favoriteEvents = eventDao.getFavoriteEvents()

    suspend fun refresh(
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

    suspend fun updateFavorite(
        id: Long,
        isFavorite: Boolean,
    ) {
        eventDao.updateFavorite(
            id = id,
            isFavorite = isFavorite,
        )
    }

    suspend fun <T> handleApiResult(func: suspend () -> Result<T>): Result<T> {
        return try {
            func()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    sealed class Result<out T> {
        data class Success<T>(val data: T) : Result<T>()
        data class Failure<T>(
            val error: Exception,
        ) : Result<T>()

        companion object {
            @JvmStatic
            fun <T> success(data: T): Result<T> = Success(data)

            @JvmStatic
            fun <T> failure(
                error: Exception,
            ): Result<T> = Failure(error)
        }
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

