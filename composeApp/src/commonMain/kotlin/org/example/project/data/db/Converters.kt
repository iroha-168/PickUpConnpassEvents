package org.example.project.data.db

import androidx.room.TypeConverter
import co.touchlab.kermit.Logger
import kotlinx.datetime.LocalDateTime

class Converters {
    @TypeConverter
    fun toDateTime(value: String?): LocalDateTime? {
        return value?.let {
            try {
                LocalDateTime.parse(it)
            } catch (e: Exception) {
                Logger.e{"HOGE: Error parsing date time: $e"}
                null
            }
        }
    }

    @TypeConverter
    fun dateTimeToString(dateTime: LocalDateTime?): String? {
        return dateTime?.toString()
    }
}