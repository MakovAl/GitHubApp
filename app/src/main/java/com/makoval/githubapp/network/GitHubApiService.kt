package com.makoval.githubapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


val BASE_URL = "https://api.github.com/"

val contentType = MediaType.get("application/json; charset=utf-8")

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
    .baseUrl(BASE_URL)
    .build()

interface GitHubApiService {
    @GET("user/repos")
    //@GET("@users/MakovAl/repos")
    suspend fun getRepositories(@Header("Authorization") _token: String): List<Repositories>

    @GET("repos/{owner}/{repository}")
    suspend fun getDetailRepositories(
        @Path("owner") owner: String,
        @Path("repository") repository: String,
        @Header("Authorization") _token: String
    ): DetailRepositories

    @GET("repos/{owner}/{repository}/contents/README.md")
    suspend fun getReadmeRepositories(
        @Path("owner") owner: String,
        @Path("repository") repository: String,
        @Header("Authorization") _token: String
    ): ContentReadme

}

object GitHubApi {
    val retrofitService: GitHubApiService by lazy {
        retrofit.create(GitHubApiService::class.java)
    }
}
