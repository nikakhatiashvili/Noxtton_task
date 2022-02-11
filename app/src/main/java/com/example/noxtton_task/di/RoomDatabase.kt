package com.example.noxtton_task.di

import android.content.Context
import androidx.room.Room
import com.example.noxtton_task.room.LocalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomDatabase {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        LocalDataBase::class.java,
        "db_name"
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: LocalDataBase) = db.userDao()

}
