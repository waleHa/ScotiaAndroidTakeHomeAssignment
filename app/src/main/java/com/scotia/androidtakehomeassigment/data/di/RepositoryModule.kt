package com.scotia.androidtakehomeassigment.data.di

import com.scotia.androidtakehomeassigment.data.datasource.GithubRemoteDataSource
import com.scotia.androidtakehomeassigment.data.repository.github.GithubRepositoryImp
import com.scotia.androidtakehomeassigment.domain.repository.github.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Provides the GithubRepository instance
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepositoryGithub(githubRemoteDataSource: GithubRemoteDataSource): GithubRepository {
        return GithubRepositoryImp(githubRemoteDataSource)
    }
}
