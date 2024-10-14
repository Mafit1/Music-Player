package com.example.musicplayer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.MusicTrackData
import com.example.domain.usecases.DeleteTrackFromTrackList
import com.example.domain.usecases.GetAllTracksOrderedByNames
import com.example.domain.usecases.AddTrackToTrackList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FullTrackListViewModel(
    private val addTrackToTrackList: AddTrackToTrackList,
    private val deleteTrackFromTrackList: DeleteTrackFromTrackList,
    private val getAllTracksOrderedByNames: GetAllTracksOrderedByNames
) : ViewModel() {

    fun getAllTracksOrderedByNames() : Flow<List<MusicTrackData>> {
        return getAllTracksOrderedByNames.execute()
    }

    fun addTrackToTrackList(newTrack: MusicTrackData) = viewModelScope.launch {
        addTrackToTrackList.execute(newTrack)
    }
}