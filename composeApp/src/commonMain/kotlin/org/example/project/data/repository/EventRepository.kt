package org.example.project.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.db.EventDto

interface EventRepository {
    val events: Flow<List<EventDto>>
    val favoriteEvents: Flow<List<EventDto>>

    suspend fun refresh(
        start: Int,
        page: Int,
        filter: ConnpassApiClient.ConnpassQueryFilter,
    ): Result<Unit>

    suspend fun updateFavorite(
        id: Long,
        isFavorite: Boolean,
    )
}