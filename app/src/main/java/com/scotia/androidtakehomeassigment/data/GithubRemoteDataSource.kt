package com.scotia.androidtakehomeassigment.data

import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRemoteDataSource {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): GithubUser

    @GET("users/{userId}/repos")
    suspend fun getRepos(@Path("userId") userId: String): List<GithubRepo>
}

