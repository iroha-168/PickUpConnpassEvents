package org.example.project.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

private val MIGRATIONS: Array<Migration> = arrayOf(
    object : Migration(1, 2) {
        override fun migrate(connection: SQLiteConnection) {
            connection.execSQL(
                "ALTER TABLE `EventDto` ADD COLUMN `url` TEXT NOT NULL DEFAULT ''"
            )
        }
    }
)

@Database(entities = [EventDto::class], version = 2)
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
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

expect fun getRoomDatabaseBuilder(): RoomDatabase.Builder<EventDatabase>