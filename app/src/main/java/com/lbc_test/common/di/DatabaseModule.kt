package com.lbc_test.common.di

import android.content.Context
import androidx.room.Room
import com.lbc_test.common.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "appDataBase"
    ).build()
}