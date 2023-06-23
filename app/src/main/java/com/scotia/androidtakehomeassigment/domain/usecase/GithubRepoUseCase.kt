package com.scotia.androidtakehomeassigment.domain.usecase

import com.scotia.androidtakehomeassigment.domain.repository.github.GithubRepository
import javax.inject.Inject

/**
 * Implementation of the [RepoUseCase] interface that retrieves a list of GitHub repositories for a user from the [GithubRepository].
 *
 * @property repository the [GithubRepository] used for accessing GitHub user and repository data
 */
class GithubRepoUseCase @Inject constructor(private val repository: GithubRepository) : RepoUseCase {
    /**
     * Retrieves a list of repositories for a GitHub user.
     *
     * @param userId the ID of the user
     * @return a list of [GithubRepo] objects representing the user's repositories
     */
    override suspend operator fun invoke(userId: String) = repository.getRepo(userId)
}
