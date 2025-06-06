package org.example.project.di

import org.example.project.data.repository.EventRepository
import org.example.project.ui.event.EventViewModel
import org.koin.dsl.module

val appModule = module {
    single { EventRepository() }
    single { EventViewModel(get()) }
}