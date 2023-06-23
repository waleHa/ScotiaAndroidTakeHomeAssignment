package com.scotia.androidtakehomeassigment.data.datasource

import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path

// Defines the Retrofit API calls to get Github user and repos
interface GithubRemoteDataSource {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): GithubUser

    @GET("users/{userId}/repos")
    suspend fun getRepo(@Path("userId") userId: String): List<GithubRepo>
}
