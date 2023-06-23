package com.scotia.androidtakehomeassigment.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a GitHub repository.
 *
 * @property name the name of the repository
 * @property description the description of the repository
 * @property updatedAt the date and time of the last update to the repository
 * @property stars the number of stars (favorites) the repository has received
 * @property forks the number of forks (copies) of the repository
 * @property owner the owner of the repository
 */
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

