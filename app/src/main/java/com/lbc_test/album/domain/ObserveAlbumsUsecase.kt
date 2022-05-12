package com.lbc_test.album.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAlbumsUsecase @Inject constructor(
    private val repository: AlbumRepository
) {

    fun execute(): Flow<PagingData<Album>> = repository.observeAlbums()
}