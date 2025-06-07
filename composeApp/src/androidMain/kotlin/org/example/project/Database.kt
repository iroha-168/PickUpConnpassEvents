package org.example.project

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.example.project.data.db.EventDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<EventDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<EventDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

