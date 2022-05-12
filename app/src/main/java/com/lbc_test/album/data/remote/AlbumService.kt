package com.lbc_test.album.data.remote

import retrofit2.http.GET

interface AlbumService {
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumResponse>
}




