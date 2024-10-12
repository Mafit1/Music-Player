package com.example.musicplayer.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.DeleteTrackFromTrackList
import com.example.domain.usecases.GetAllTracksOrderedByNames
import com.example.domain.usecases.AddTrackToTrackList

class FullTrackListViewModel(
    private val addTrackToTrackList: AddTrackToTrackList,
    private val deleteTrackFromTrackList: DeleteTrackFromTrackList,
    private val getAllTracksOrderedByNames: GetAllTracksOrderedByNames
) : ViewModel() {

}