package com.scotia.androidtakehomeassigment.data.datasource

import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for accessing GitHub API.
 */
interface GithubRemoteDataSource {
    /**
     * Retrieves information about a GitHub user.
     *
     * @param userId the ID of the user
     * @return the [GithubUser] object representing the user
     */
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): GithubUser

    /**
     * Retrieves a list of repositories for a GitHub user.
     *
     * @param userId the ID of the user
     * @return a list of [GithubRepo] objects representing the user's repositories
     */
    @GET("users/{userId}/repos")
    suspend fun getRepo(@Path("userId") userId: String): List<GithubRepo>
}
