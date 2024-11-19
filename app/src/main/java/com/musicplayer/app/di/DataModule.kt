package com.musicplayer.app.di

import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.musicplayer.data.MusicPlayerDataBase
import com.musicplayer.data.dao.MusicTrackDAO
import com.musicplayer.data.dao.PlaylistDAO
import com.musicplayer.data.repositories.MusicTrackRepositoryImpl
import com.musicplayer.data.repositories.PlayerManagerRepositoryImpl
import com.musicplayer.data.repositories.PlaylistRepositoryImpl
import com.musicplayer.domain.repositories.MusicTrackRepository
import com.musicplayer.domain.repositories.PlayerManagerRepository
import com.musicplayer.domain.repositories.PlaylistRepository
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

    factory<PlaylistRepository> {
        PlaylistRepositoryImpl(
            playlistDAO = get()
        )
    }

    single<ExoPlayer> {
        ExoPlayer.Builder(get())
            .build()
            .apply {
                playWhenReady = true
            }
    }

    single<PlayerManagerRepository> {
        PlayerManagerRepositoryImpl(
            exoPlayer = get()
        )
    }
}