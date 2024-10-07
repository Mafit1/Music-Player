package com.example.musicplayer.di

import androidx.room.Room
import com.example.data.MusicPlayerDataBase
import org.koin.dsl.module

val dataModule = module {
    single<MusicPlayerDataBase> {
        Room.databaseBuilder(
            context = get(),
            MusicPlayerDataBase::class.java,
            "music_player.db"
        ).build()
    }
}