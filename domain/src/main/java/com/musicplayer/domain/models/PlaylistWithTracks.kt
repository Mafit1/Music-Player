package com.musicplayer.domain.models

data class PlaylistWithTracks(
    var playlistInfo: PlaylistInfo,
    var musicTracks: List<MusicTrackData>
)