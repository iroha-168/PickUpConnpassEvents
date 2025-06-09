package org.example.project.di

import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.db.EventDao
import org.example.project.data.db.EventDatabase
import org.example.project.data.db.getRoomDatabase
import org.example.project.data.db.getRoomDatabaseBuilder
import org.example.project.data.repository.EventRepository
import org.example.project.ui.event.EventViewModel
import org.example.project.ui.favorite.FavoriteViewModel
import org.koin.dsl.module

val appModule = module {
    single { EventRepository(get(), get()) }
    single { EventViewModel(get()) }
    single { FavoriteViewModel(get()) }
    single { ConnpassApiClient() }

    single<EventDatabase> {
        getRoomDatabase(getRoomDatabaseBuilder())
    }

    single<EventDao> {
        val database = get<EventDatabase>()
        database.getDao()
    }
}