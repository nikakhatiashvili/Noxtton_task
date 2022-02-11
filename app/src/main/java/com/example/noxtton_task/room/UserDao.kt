package com.example.noxtton_task.room

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noxtton_task.model.Item
import com.example.noxtton_task.model.repo.RepositorySearch

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(vararg shirt: Item)


    @Query("SELECT EXISTS (SELECT 1 FROM shirts WHERE id = :id)")
    fun exists(id: Int): Boolean

    @Query("SELECT * FROM shirts")
    fun readAllData(): LiveData<List<Item>>


    @Query("DELETE FROM shirts WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM search")
    fun getAllSearch():LiveData<List<RepositorySearch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearch(vararg shirt: RepositorySearch)

    @Query("SELECT EXISTS (SELECT 1 FROM search WHERE searchName = :id)")
    suspend fun searchExist(id: String): Boolean

    @Query("DELETE FROM search")
    suspend fun deleteAll()
}