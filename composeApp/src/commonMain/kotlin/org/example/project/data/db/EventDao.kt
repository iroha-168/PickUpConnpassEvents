package org.example.project.data.db
import androidx.room.Dao
import androidx.room.Insert


@Dao
interface EventDao {
    @Insert
    suspend fun eventInsert(event: List<EventDto>)
}