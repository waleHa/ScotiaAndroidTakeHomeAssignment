package com.scotia.androidtakehomeassigment.domain.repository.github

import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import retrofit2.http.Path

/**
 * Interface for accessing GitHub user and repository data.
 */
interface GithubRepository {
    /**
     * Retrieves information about a GitHub user.
     *
     * @param userId the ID of the user
     * @return the [GithubUser] object representing the user
     */
    suspend fun getUser(userId: String): GithubUser

    /**
     * Retrieves a list of repositories for a GitHub user.
     *
     * @param userId the ID of the user
     * @return a list of [GithubRepo] objects representing the user's repositories
     */
    suspend fun getRepo(userId: String): List<GithubRepo>
}
