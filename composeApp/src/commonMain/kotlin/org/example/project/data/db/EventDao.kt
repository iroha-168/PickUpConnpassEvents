package org.example.project.data.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface EventDao {
    @Update
    suspend fun updateEvent(event: EventDto)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: EventDto): Long

    @Query("SELECT * FROM EventDto")
    fun getAll(): Flow<List<EventDto>>
}