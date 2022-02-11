package com.example.noxtton_task.model.repo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search")
data class RepositorySearch(
    val searchName: String?,
){
    @PrimaryKey(autoGenerate = true) var uid: Int = 0

}
