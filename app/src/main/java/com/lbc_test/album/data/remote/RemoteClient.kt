package com.lbc_test.album.data.remote

import javax.inject.Inject

class RemoteClient @Inject constructor(
    private val albumService: AlbumService
) {

    suspend fun getAlbums() = albumService.getAlbums()
}