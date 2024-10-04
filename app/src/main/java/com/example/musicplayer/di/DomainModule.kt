package com.example.musicplayer.di

import com.example.domain.usecases.DeleteTrackFromTrackList
import org.koin.dsl.module

val domainModule = module {
    factory<DeleteTrackFromTrackList> {
        DeleteTrackFromTrackList(repository = get())
    }
}