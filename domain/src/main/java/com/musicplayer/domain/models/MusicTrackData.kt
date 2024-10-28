package com.musicplayer.domain.models

data class MusicTrackData(
    var id : Int? = null,
    var name : String,
    var author : String,
    var durationSec : Long,
    var playlistId : Int? = null
)