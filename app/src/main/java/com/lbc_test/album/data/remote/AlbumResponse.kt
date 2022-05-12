package com.lbc_test.album.data.remote

data class AlbumResponse(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
