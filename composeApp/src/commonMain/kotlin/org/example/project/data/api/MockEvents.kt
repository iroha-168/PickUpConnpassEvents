package org.example.project.data.api

import kotlinx.datetime.LocalDateTime
import org.example.project.data.entity.DateTime
import org.example.project.data.entity.Event
import org.example.project.data.entity.EventResponse
import kotlin.time.ExperimentalTime

// モック
// API keyをもらうまでの間はモックを使う
@OptIn(ExperimentalTime::class)
val mockEventsList = listOf(
    // 関係ないイベント
    Event(
        id = 1L,
        title = "PHPer Meetup",
        startedAt = DateTime(LocalDateTime.parse("2025-06-08T14:30:00")),
        place = "Tokyo",
    ),
    // Android, iOS系のイベント
    Event(
        id = 2L,
        title = "Android Kotlin Multiplatform Meetup",
        startedAt = DateTime(LocalDateTime.parse("2025-06-08T14:30:00")),
        place = "Tokyo",
    ),
    Event(
        id = 3L,
        title = "Android Jetpack Compose Workshop",
        startedAt = DateTime(LocalDateTime.parse("2025-06-09T14:30:00")),
        place = "Osaka",
    ),
    Event(
        id = 4L,
        title = "iOS SwiftUI勉強会",
        startedAt = null,
        place = null,
    ),
    Event(
        id = 5L,
        title = "iOSDC 2025 After Party",
        startedAt = DateTime(LocalDateTime.parse("2025-10-01T14:30:00")),
        place = null,
    )
)
val mockEvents = EventResponse(
    events = mockEventsList
)