package com.lbc_test.album.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albumEntities: List<AlbumEntity>)

    @Query("SELECT * FROM albums ORDER BY id DESC")
    fun observeAlbums(): PagingSource<Int, AlbumEntity>
}