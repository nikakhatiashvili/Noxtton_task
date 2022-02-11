package com.example.noxtton_task.network


import com.example.noxtton_task.model.GithubRepo
import com.example.noxtton_task.model.Item

import dagger.hilt.internal.GeneratedEntryPoint
import retrofit2.Response
import retrofit2.http.*
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.GET




interface GithubNetwork {


    @Headers("Authorization: $BASE_GITHUB_TOKEN")
    @GET("search/repositories")
    suspend fun getRepo(@Query("q") repository: String):Response<GithubRepo>

    @GET("users/{name}/repos")
    suspend fun getUserRepo(@Path("") name:String)

    @GET("users/{name}/repos")
    suspend fun getUserByName(@Path("name") name: String?): Response<List<Item>>?
}