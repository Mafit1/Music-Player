package com.example.musicplayer.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.DeleteTrackFromTrackList

class FullTrackListViewModel(
    private val deleteTrackFromTrackList: DeleteTrackFromTrackList
) : ViewModel() {

}