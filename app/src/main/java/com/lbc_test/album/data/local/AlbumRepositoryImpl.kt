package com.lbc_test.album.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lbc_test.album.data.remote.RemoteClient
import com.lbc_test.album.data.toDomain
import com.lbc_test.album.data.toEntity
import com.lbc_test.album.domain.Album
import com.lbc_test.album.domain.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlbumRepositoryImpl(
    private val remoteSource: RemoteClient,
    private val localSource: AlbumDao,
) : AlbumRepository {
    override fun observeAlbums(): Flow<PagingData<Album>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
            )
        ) {
            localSource.observeAlbums()
        }.flow.map { albums -> albums.map { it.toDomain() } }
    }

    override suspend fun refreshAlbums(): Boolean {
        return try {
            val albums = remoteSource.getAlbums()
            localSource.insertAlbums(albums.map { it.toEntity() })
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}