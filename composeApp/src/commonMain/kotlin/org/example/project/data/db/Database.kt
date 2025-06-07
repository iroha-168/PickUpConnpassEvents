package org.example.project.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

private val MIGRATIONS: Array<Migration> = arrayOf()

@Database(entities = [EventDto::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun getDao(): EventDao
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<EventDatabase>
): EventDatabase {
    return builder
        .addMigrations(*MIGRATIONS)
        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
