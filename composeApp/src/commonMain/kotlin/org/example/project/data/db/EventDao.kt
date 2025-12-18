package org.example.project.data.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime


@Dao
interface EventDao {
    @Query("UPDATE EventDto SET title = :title, url = :url, startedAt = :startAt, place = :place WHERE id = :id")
    suspend fun updateEvent(
        id: Long,
        title: String,
        url: String,
        startAt: LocalDateTime?,
        place: String?
    )

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: EventDto): Long

    @Query("SELECT * FROM EventDto")
    fun getAll(): Flow<List<EventDto>>

    @Query("UPDATE EventDto SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM EventDto WHERE isFavorite = 1")
    fun getFavoriteEvents(): Flow<List<EventDto>>

    @Query("SELECT * FROM EventDto WHERE place = 'オンライン'")
    fun getOnlineEvents(): Flow<List<EventDto>>
}
