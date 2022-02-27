package com.makoval.githubapp.network

import kotlinx.serialization.Serializable

@Serializable
data class DetailRepositories(
    val html_url: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val license: License?,
    val watchers_count: Int
)

@Serializable
data class License(
    val name: String
)

@Serializable
data class ContentReadme(
    val content: String
)
