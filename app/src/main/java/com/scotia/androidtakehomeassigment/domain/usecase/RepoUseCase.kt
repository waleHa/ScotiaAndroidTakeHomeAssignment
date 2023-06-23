package com.scotia.androidtakehomeassigment.domain.usecase

import com.scotia.androidtakehomeassigment.domain.model.GithubRepo

/**
 * Interface for performing repository-related operations.
 */
interface RepoUseCase {
    /**
     * Retrieves a list of repositories for a user.
     *
     * @param userId the ID of the user
     * @return a list of [GithubRepo] objects representing the user's repositories
     */
    suspend operator fun invoke(userId: String): List<GithubRepo>
}

