package com.lbc_test.album.di

import com.lbc_test.album.data.remote.AlbumService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteClientModule {

    @Singleton
    @Provides
    fun provideAlbumService(retrofit: Retrofit) = retrofit.create(AlbumService::class.java)
}