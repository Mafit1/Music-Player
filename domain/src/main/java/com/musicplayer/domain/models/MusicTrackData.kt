package com.musicplayer.domain.models

data class MusicTrackData(
    var id : Int = 0,
    var name : String,
    var author : String,
    var duration : Long,
    var filePath: String = ""
)