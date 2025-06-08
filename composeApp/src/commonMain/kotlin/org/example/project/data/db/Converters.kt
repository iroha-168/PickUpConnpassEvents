package org.example.project.data.db

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime
import org.example.project.data.entity.DateTime

class Converters {
    @TypeConverter
    fun toDateTime(value: String?): DateTime? {
        return value?.let { DateTime(LocalDateTime.parse(it)) }
    }

    @TypeConverter
    fun dateTimeToString(dateTime: DateTime?): String? {
        return dateTime?.date?.toString()
    }
}