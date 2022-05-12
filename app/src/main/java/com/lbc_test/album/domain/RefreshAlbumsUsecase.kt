package com.lbc_test.album.domain

import javax.inject.Inject

class RefreshAlbumsUsecase @Inject constructor(
    private val repository: AlbumRepository
) {

    suspend fun execute(): Boolean = repository.refreshAlbums()
}