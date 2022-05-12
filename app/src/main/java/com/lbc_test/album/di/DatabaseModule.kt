package com.lbc_test.album.di

import com.lbc_test.common.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAlbumDao(appDatabase: AppDatabase) = appDatabase.albumDao()
}