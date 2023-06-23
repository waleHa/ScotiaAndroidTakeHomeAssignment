package com.scotia.androidtakehomeassigment.domain.repository.github

import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import retrofit2.http.Path

interface GithubRepository {
    suspend fun getUser(userId: String): GithubUser
    suspend fun getRepo(userId: String): List<GithubRepo>
}