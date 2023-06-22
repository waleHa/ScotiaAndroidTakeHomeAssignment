package com.scotia.androidtakehomeassigment.domain.repository.github

import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser

interface GithubRepository {
    suspend fun getUser(userId: String): GithubUser
    suspend fun getRepos(userId: String): List<GithubRepo>
}