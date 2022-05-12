package com.lbc_test.common.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.lbc_test.album.data.local.AlbumDao
import com.lbc_test.album.data.local.AlbumEntity

@Database(
    entities = [
        AlbumEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}