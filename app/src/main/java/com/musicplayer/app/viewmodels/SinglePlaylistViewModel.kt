package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.usecases.GetTracksFromPlaylistOrderedByNames
import kotlinx.coroutines.flow.Flow

class SinglePlaylistViewModel(
    private val getTracksFromPlaylistOrderedByNames: GetTracksFromPlaylistOrderedByNames
) : ViewModel() {

    fun getTracks(playlistId: Int?): Flow<List<MusicTrackData>> {
        return getTracksFromPlaylistOrderedByNames.execute(playlistId)
    }

}