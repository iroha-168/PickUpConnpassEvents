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
    suspend fun hoge(): String {
        delay(timeMillis = 1000)
        val result = connpassApiClient.getSample()
        Logger.d { "HOGE: result is $result" }

//        return "hoge!"
        return result
    }

    suspend fun insertEvents(events: List<EventDto>) {
        eventDao.eventInsert(events)
        Logger.d { "HOGE: insertEventsが実行されました" }
    }
}