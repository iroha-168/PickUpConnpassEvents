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

private val MIGRATIONS: Array<Migration> = arrayOf()

//private val MIGRATIONS: Array<Migration> = arrayOf(
//    object : Migration(2, 3) {
//        override fun migrate(connection: SQLiteConnection) {
//            connection.execSQL(
//                "ALTER TABLE `EventDto` ADD COLUMN `address` TEXT"
//            )
//        }
//    }
//)

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
        // FIXME: マイグレーションが見つからない場合既存のDBを破棄して新規作成し直す。本番環境ではよくないのでリリースする場合は削除する
//        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

expect fun getRoomDatabaseBuilder(): RoomDatabase.Builder<EventDatabase>