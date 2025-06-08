package org.example.project.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

private val MIGRATIONS: Array<Migration> = arrayOf()

@Database(entities = [EventDto::class], version = 1)
@ConstructedBy(EventDatabaseConstructor::class)
@TypeConverters(Converters::class)
abstract class EventDatabase : RoomDatabase() {
    abstract fun getDao(): EventDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object EventDatabaseConstructor : RoomDatabaseConstructor<EventDatabase> {
    override fun initialize(): EventDatabase
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

expect fun getRoomDatabaseBuilder(): RoomDatabase.Builder<EventDatabase>