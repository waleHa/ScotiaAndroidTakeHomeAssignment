package com.scotia.androidtakehomeassigment.domain.usecase

import com.scotia.androidtakehomeassigment.domain.repository.github.GithubRepository
import javax.inject.Inject
// Use Cases that invoke the GithubUser methods
class GithubUserUseCase @Inject constructor(private val repository: GithubRepository) {
    suspend operator fun invoke(userId: String) = repository.getUser(userId)
}
