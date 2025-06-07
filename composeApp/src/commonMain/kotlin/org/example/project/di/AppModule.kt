package org.example.project.di

import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.repository.EventRepository
import org.example.project.ui.event.EventViewModel
import org.koin.dsl.module

val appModule = module {
    single { EventRepository(get(), get()) }
    single { EventViewModel(get()) }
    single { ConnpassApiClient() }

    // TODO: データベースのインスタンス化をする
//    single<EventDatabase> {
//    }

//    single<EventDao> {
//        val database = get<EventDatabase>()
//        database.getDao()
//    }
}