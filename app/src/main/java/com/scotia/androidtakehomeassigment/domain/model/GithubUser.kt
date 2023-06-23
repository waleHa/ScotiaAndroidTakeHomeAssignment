package com.scotia.androidtakehomeassigment.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class GithubUser(
    @Json(name = "name") val name: String?,
    @Json(name = "avatar_url") val avatarUrl: String?
): Parcelable