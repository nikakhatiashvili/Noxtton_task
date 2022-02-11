package com.example.noxtton_task.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noxtton_task.model.Item

import com.example.noxtton_task.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository:GithubRepository) : ViewModel() {
    private var _repositories = MutableLiveData<List<Item>>()
    val repositories: LiveData<List<Item>> = _repositories
    fun getUserRepo(user:String){
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.getUserRepo(user)
            if (!items.data.isNullOrEmpty()){
                _repositories.postValue(items.data!!)
            }
        }
    }
}