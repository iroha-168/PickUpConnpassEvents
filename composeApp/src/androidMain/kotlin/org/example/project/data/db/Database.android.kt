package org.example.project.data.db

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.context.GlobalContext

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<EventDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    val builder = Room.databaseBuilder<EventDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
    Log.d("HOGE", "builder: $builder")
    return builder
}

actual fun getRoomDatabaseBuilder(): RoomDatabase.Builder<EventDatabase> {
    val scope = GlobalContext.get()
    val context: Context = scope.get()
    Log.d("HOGE", "context: $context")
    return getDatabaseBuilder(ctx = context)
}