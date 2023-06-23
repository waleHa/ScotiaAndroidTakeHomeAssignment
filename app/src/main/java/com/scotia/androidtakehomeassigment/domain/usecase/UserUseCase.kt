package com.scotia.androidtakehomeassigment.domain.usecase

import com.scotia.androidtakehomeassigment.domain.model.GithubUser

/**
 * Interface for performing user-related operations.
 */
interface UserUseCase {
    /**
     * Retrieves information about a GitHub user.
     *
     * @param userId the ID of the user
     * @return the [GithubUser] object representing the user
     */
    suspend operator fun invoke(userId: String): GithubUser

    /**
     * Retrieves the total number of forks across all repositories for a GitHub user.
     *
     * @param userId the ID of the user
     * @return the total number of forks
     */
    suspend fun getTotalForks(userId: String): Int

    /**
     * Retrieves a badge indicating the total number of forks for a GitHub user.
     *
     * @param userId the ID of the user
     * @return a badge string indicating the total number of forks
     */
    suspend fun getTotalForksBadge(userId: String): String
}

