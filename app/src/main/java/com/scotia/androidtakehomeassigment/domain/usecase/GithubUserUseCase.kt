package com.scotia.androidtakehomeassigment.domain.usecase

import com.scotia.androidtakehomeassigment.domain.repository.github.GithubRepository
import javax.inject.Inject

/**
 * Implementation of the [UserUseCase] interface that retrieves GitHub user information and performs user-related operations using the [GithubRepository].
 *
 * @property repository the [GithubRepository] used for accessing GitHub user and repository data
 */
class GithubUserUseCase @Inject constructor(private val repository: GithubRepository) : UserUseCase {
    /**
     * Retrieves information about a GitHub user.
     *
     * @param userId the ID of the user
     * @return the [GithubUser] object representing the user
     */
    override suspend operator fun invoke(userId: String) = repository.getUser(userId)

    /**
     * Retrieves the total number of forks across all repositories for a GitHub user.
     *
     * @param userId the ID of the user
     * @return the total number of forks
     */
    override suspend fun getTotalForks(userId: String): Int {
        val repos = repository.getRepo(userId)
        return repos.sumOf { requireNotNull(it.forks) }
    }

    /**
     * Retrieves a badge indicating the total number of forks for a GitHub user.
     *
     * @param userId the ID of the user
     * @return a badge string indicating the total number of forks
     */
    override suspend fun getTotalForksBadge(userId: String): String {
        val forks = getTotalForks(userId)
        return if (forks > 5000) {
            "⭐ $forks Forks " // Indicate with a star
        } else {
            ""
        }
    }
}
