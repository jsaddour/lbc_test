package com.lbc_test.album.di

import com.lbc_test.album.data.local.AlbumDao
import com.lbc_test.album.data.local.AlbumRepositoryImpl
import com.lbc_test.album.data.remote.RemoteClient
import com.lbc_test.album.domain.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AlbumRepositoryModule {

    @Provides
    fun provideAlbumRepository(
        remoteClient: RemoteClient,
        dao: AlbumDao
    ): AlbumRepository = AlbumRepositoryImpl(
        remoteSource = remoteClient,
        localSource = dao
    )
}