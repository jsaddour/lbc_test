package com.lbc_test.album.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


interface AlbumRepository {
    fun observeAlbums(): Flow<PagingData<Album>>
    suspend fun refreshAlbums(): Boolean
}