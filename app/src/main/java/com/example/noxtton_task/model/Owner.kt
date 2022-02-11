package com.example.noxtton_task.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val avatar_url: String?,
    val gravatar_id: String?,
    @Json(name = "html_url")
    val htmlurls: String?,
    val login: String?,
    val node_id: String?,
    val repos_url: String?,
    val type: String?,
    @Json(name = "url")
    val urls: String?
):Parcelable