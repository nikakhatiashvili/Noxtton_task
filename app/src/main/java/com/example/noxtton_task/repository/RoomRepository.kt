package com.example.noxtton_task.repository

import androidx.lifecycle.LiveData
import com.example.noxtton_task.model.Item
import com.example.noxtton_task.model.repo.RepositorySearch
import com.example.noxtton_task.network.GithubNetwork
import com.example.noxtton_task.room.UserDao
import javax.inject.Inject

class RoomRepository@Inject constructor(private val userDao: UserDao) {
    suspend fun addItem(item:Item){
        userDao.addItem(item)
    }
    suspend fun deleteItem(id:Int){
        userDao.deleteById(id)
    }

    suspend fun addSearch(code:RepositorySearch){
        userDao.addSearch(code)
    }
    suspend fun searchExists(str:String)=  userDao.searchExist(str)

     fun getSearch():LiveData<List<RepositorySearch>>{
        return  userDao.getAllSearch()
    }
      fun exists(id:Int)= userDao.exists(id)

     fun favoriteItems(): LiveData<List<Item>> {
        return userDao.readAllData()
    }
}