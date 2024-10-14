package com.example.musicplayer.di

import com.example.domain.usecases.AddTrackToTrackList
import com.example.domain.usecases.DeleteTrackFromTrackList
import com.example.domain.usecases.GetAllTracksOrderedByNames
import org.koin.dsl.module

val domainModule = module {
    factory<DeleteTrackFromTrackList> {
        DeleteTrackFromTrackList(repository = get())
    }
    factory<AddTrackToTrackList> {
        AddTrackToTrackList(repository = get())
    }
    factory<GetAllTracksOrderedByNames> {
        GetAllTracksOrderedByNames(repository = get())
    }
}