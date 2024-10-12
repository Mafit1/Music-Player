package com.example.domain.models

data class MusicTrackData(
    var id : Int,
    var name : String,
    var author : String,
    var durationSec : Long,
    var playlistId : Int?
)