package com.scotia.androidtakehomeassigment.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class GithubRepo(
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "updated_at") val updatedAt: String?,
    @Json(name = "stargazers_count") val stars: Int?,
    @Json(name = "forks") val forks: Int?,
    @Json(name = "owner") val owner: GithubOwner?
) : Parcelable
