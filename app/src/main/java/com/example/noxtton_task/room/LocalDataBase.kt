package com.example.noxtton_task.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noxtton_task.model.Item
import com.example.noxtton_task.model.repo.RepositorySearch


@Database(entities = [Item::class, RepositorySearch::class], version =3 )
abstract class LocalDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
}