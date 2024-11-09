package com.musicplayer.domain.repositories

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import kotlinx.coroutines.flow.Flow
import java.io.File

interface MusicTrackRepository {

    suspend fun addMusicTrack(newTrack: MusicTrackData)

    suspend fun addMusicTracks(musicTracksList: List<MusicTrackData>)

    suspend fun deleteMusicTrack(trackToDelete: MusicTrackData)

    fun getAllTracksOrderedByNames(): Flow<List<MusicTrackData>>

    suspend fun setPlaylistToTrack(playlist: PlaylistInfo, musicTrackId: Int)

    suspend fun saveTrackFile(file: File)

}