package com.scotia.androidtakehomeassigment.domain.usecase

import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import com.scotia.androidtakehomeassigment.domain.repository.github.GithubRepository
import javax.inject.Inject
// Use Cases that invoke the GithubUser methods
class GithubUserUseCase @Inject constructor(private val repository: GithubRepository) {
    suspend operator fun invoke(userId: String) = repository.getUser(userId)

    suspend fun getTotalForks(userId: String): Int {
        val repos = repository.getRepo(userId)
        return repos.sumOf { requireNotNull(it.forks) }
    }

    suspend fun getTotalForksBadge(userId: String): String {
        val forks = getTotalForks(userId)
        return if (forks > 5000) {
            "⭐ $forks Forks " // Indicate with a star
        } else {""}
    }
}
