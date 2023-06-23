package com.scotia.androidtakehomeassigment.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a GitHub owner.
 *
 * @property login the login name of the owner
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class GithubOwner(
    @Json(name = "login") val login: String?
) : Parcelable
