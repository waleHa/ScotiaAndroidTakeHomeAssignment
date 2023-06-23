package com.scotia.androidtakehomeassigment.data.repository.github

import com.scotia.androidtakehomeassigment.data.datasource.GithubRemoteDataSource
import com.scotia.androidtakehomeassigment.domain.repository.github.GithubRepository
import retrofit2.http.Path
import javax.inject.Inject

class GithubRepositoryImp @Inject constructor(private val dataSource: GithubRemoteDataSource): GithubRepository {
    override suspend fun getUser(userId: String) = dataSource.getUser(userId)
    override suspend fun getRepo(userId: String) = dataSource.getRepo(userId)
}