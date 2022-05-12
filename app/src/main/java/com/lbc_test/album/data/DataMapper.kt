package com.lbc_test.album.data

import com.lbc_test.album.data.local.AlbumEntity
import com.lbc_test.album.data.remote.AlbumResponse
import com.lbc_test.album.domain.Album

fun AlbumResponse.toEntity() = AlbumEntity(
    id = id,
    albumId = albumId,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)

fun AlbumEntity.toDomain() = Album(
    id = id,
    albumId = albumId,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)