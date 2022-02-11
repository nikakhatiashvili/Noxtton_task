package com.example.noxtton_task.repository

import androidx.lifecycle.LiveData
import com.example.noxtton_task.model.GithubRepo
import com.example.noxtton_task.model.Item
import com.example.noxtton_task.network.GithubNetwork
import com.example.noxtton_task.util.Resource
import retrofit2.Response
import javax.inject.Inject

class GithubRepository@Inject constructor(private val githubNetwork: GithubNetwork) {

    suspend fun getRepo(code:String)= handleResponse { githubNetwork.getRepo(code) }



    suspend fun getUserRepo(user:String)= handleResponse { githubNetwork.getUserByName(user)!! }

}
private suspend fun <T> handleResponse(apiCall: suspend() -> Response<T>): Resource<T> {

    try {
        val response = apiCall()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            return Resource.Success(body)
        }
        return Resource.Error(response.errorBody().toString())

    }catch (e: Exception) {
        return Resource.Error("exception")
    }
}