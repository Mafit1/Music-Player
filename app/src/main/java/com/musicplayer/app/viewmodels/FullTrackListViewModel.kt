package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.usecases.DeleteTrackFromTrackList
import com.musicplayer.domain.usecases.GetAllTracksOrderedByNames
import com.musicplayer.domain.usecases.AddTrackToTrackList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FullTrackListViewModel(
    private val getAllTracksOrderedByNames: GetAllTracksOrderedByNames,
    private val addTrackToTrackList: AddTrackToTrackList,
    private val deleteTrackFromTrackList: DeleteTrackFromTrackList
) : ViewModel() {

    fun getAllTracksOrderedByNames() : Flow<List<MusicTrackData>> {
        return getAllTracksOrderedByNames.execute()
    }

    fun addTrackToTrackList(newTrack: MusicTrackData) = viewModelScope.launch {
        addTrackToTrackList.execute(newTrack)
    }

    fun deleteTrack(trackToDelete: MusicTrackData) = viewModelScope.launch {
        deleteTrackFromTrackList.execute(trackToDelete)
    }
}