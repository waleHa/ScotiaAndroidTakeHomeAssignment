package com.scotia.androidtakehomeassigment.data.repository.github

import com.scotia.androidtakehomeassigment.data.datasource.GithubRemoteDataSource
import com.scotia.androidtakehomeassigment.domain.repository.github.GithubRepository
import retrofit2.http.Path
import javax.inject.Inject
/**
 * Implementation of the [GithubRepository] interface that retrieves user and repository data from the [GithubRemoteDataSource].
 *
 * @property dataSource the [GithubRemoteDataSource] used for accessing the GitHub API
 */
class GithubRepositoryImp @Inject constructor(private val dataSource: GithubRemoteDataSource) : GithubRepository {
    /**
     * Retrieves information about a GitHub user.
     *
     * @param userId the ID of the user
     * @return the [GithubUser] object representing the user
     */
    override suspend fun getUser(userId: String) = dataSource.getUser(userId)

    /**
     * Retrieves a list of repositories for a GitHub user.
     *
     * @param userId the ID of the user
     * @return a list of [GithubRepo] objects representing the user's repositories
     */
    override suspend fun getRepo(userId: String) = dataSource.getRepo(userId)
}
