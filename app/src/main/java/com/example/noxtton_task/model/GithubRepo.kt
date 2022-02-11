package com.example.noxtton_task.model

data class GithubRepo(
    val incomplete_results: Boolean?,
    val items: List<Item>?,
    val total_count: Int?
)