package org.example.project.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.project.data.entity.DateTime

@Entity
data class EventDto(
    @PrimaryKey
    val id: Long,
    val title: String,
    val url: String,
//    val startedAt: DateTime?, // Converterを作る必要あり
    val place: String?,
    val isFavorite: Boolean
)