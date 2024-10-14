package com.example.musicplayer.di

import androidx.room.Room
import com.example.data.MusicPlayerDataBase
import com.example.data.dao.MusicTrackDAO
import com.example.data.dao.PlaylistDAO
import com.example.data.repositories.MusicTrackRepositoryImpl
import com.example.data.repositories.PlaylistRepositoryImpl
import com.example.domain.repositories.MusicTrackRepository
import org.koin.dsl.module

val dataModule = module {
    single<MusicPlayerDataBase> {
        Room.databaseBuilder(
            context = get(),
            MusicPlayerDataBase::class.java,
            "music_player.db"
        ).build()
    }

    single<MusicTrackDAO> {
        val db = get<MusicPlayerDataBase>()
        db.musicTrackDAO()
    }

    single<PlaylistDAO> {
        val db = get<MusicPlayerDataBase>()
        db.playlistDAO()
    }

    factory<MusicTrackRepository> {
        MusicTrackRepositoryImpl(
            musicTrackDAO = get()
        )
    }

    factory<PlaylistRepositoryImpl> {
        PlaylistRepositoryImpl(
            playlistDAO = get()
        )
    }
}