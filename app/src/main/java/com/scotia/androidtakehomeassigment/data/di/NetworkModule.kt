package com.scotia.androidtakehomeassigment.data.di

import com.scotia.androidtakehomeassigment.data.datasource.GithubRemoteDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // Provides Moshi instance
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Provides Converter Factory instance

    @Provides
    @Reusable
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    // Provides Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit( factory: Converter.Factory): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(factory)
        .build()

    // Provides GithubRemoteDataSource instance
    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubRemoteDataSource =
        retrofit.create(GithubRemoteDataSource::class.java)
}
