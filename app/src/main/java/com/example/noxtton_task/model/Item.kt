package com.example.noxtton_task.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "shirts")
data class Item(
    val allow_forking: Boolean?,
    val archived: Boolean?,
    val created_at: String?,
    val default_branch: String?,
    val description: String?,
    val disabled: Boolean?,
    val fork: Boolean?,
    val forks: Int?,
    val forks_count: Int?,
    val full_name: String?,
    val has_projects: Boolean?,
    val has_wiki: Boolean?,
    val homepage: String?,
    val html_url: String?,
    @PrimaryKey
    val id: Int?,
    val is_template: Boolean?,
    val language: String?,
    val name: String?,
    var existsInRoom:Boolean? = false,
    val open_issues_count: Int?,
    @Embedded val owner: Owner?,

    val pushed_at: String?,

    val size: Int?,
    val stargazers_count: Int?,
    val updated_at: String?,
    val url: String?,
    val visibility: String?,
    val watchers: Int?,
    val watchers_count: Int?
):Parcelable