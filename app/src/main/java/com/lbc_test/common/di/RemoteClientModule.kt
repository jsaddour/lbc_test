package com.lbc_test.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteClientModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder().apply {
        baseUrl("https://static.leboncoin.fr/")
        client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
        addConverterFactory(GsonConverterFactory.create())
    }.build()
}