package com.scotia.androidtakehomeassigment.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubUser(
    @Json(name = "name") val name: String?,
    @Json(name = "avatar_url") val avatarUrl: String?
)