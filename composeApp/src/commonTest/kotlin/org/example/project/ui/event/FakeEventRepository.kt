package org.example.project.ui.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.project.data.db.EventDto
import org.example.project.data.repository.EventRepository
import org.example.project.data.repository.Result

class FakeEventRepository() : EventRepository {
    override val favoriteEvents: Flow<List<EventDto>> = MutableStateFlow(emptyList())
    override val events: StateFlow<List<EventDto>> = MutableStateFlow(emptyList())

    var refreshResult: Result<Unit> = Result.success(Unit)

    override suspend fun refresh(start: Int, page: Int): Result<Unit> {
        return refreshResult
    }

    override suspend fun updateFavorite(id: Long, isFavorite: Boolean) {
        // Do nothing
    }
}