package com.musicplayer.app.di

import com.musicplayer.domain.usecases.AddNewPlaylist
import com.musicplayer.domain.usecases.AddTrackToTrackList
import com.musicplayer.domain.usecases.DeletePlaylist
import com.musicplayer.domain.usecases.DeleteTrackFromTrackList
import com.musicplayer.domain.usecases.GetAllPlaylistsOrderedByNames
import com.musicplayer.domain.usecases.GetAllTracksOrderedByNames
import com.musicplayer.domain.usecases.GetTracksFromPlaylistOrderedByNames
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
    factory<GetAllPlaylistsOrderedByNames> {
        GetAllPlaylistsOrderedByNames(repository = get())
    }
    factory<GetTracksFromPlaylistOrderedByNames> {
        GetTracksFromPlaylistOrderedByNames(repository = get())
    }
    factory<AddNewPlaylist> {
        AddNewPlaylist(repository = get())
    }
    factory<DeletePlaylist> {
        DeletePlaylist(repository = get())
    }
}