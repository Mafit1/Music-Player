package com.example.musicplayer.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.DeleteTrackFromTrackList
import com.example.domain.usecases.GetAllTracksOrderedByNames

class FullTrackListViewModel(
    private val deleteTrackFromTrackList: DeleteTrackFromTrackList,
    private val getAllTracksOrderedByNames: GetAllTracksOrderedByNames
) : ViewModel() {

}