package com.makoval.githubapp.network

import kotlinx.serialization.Serializable


@Serializable
data class Repositories(
    val name: String,
    val description: String?,
    val language: String?,
    val owner: Owner
) : java.io.Serializable


@Serializable
data class Owner(
    val login: String
)
