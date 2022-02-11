package com.example.noxtton_test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noxtton_task.model.Item
import com.example.noxtton_task.model.repo.RepositorySearch
import com.example.noxtton_task.repository.GithubRepository
import com.example.noxtton_task.repository.RoomRepository
import com.example.noxtton_task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: GithubRepository,private val roomRepository: RoomRepository)  : ViewModel() {

    private var _repositories = MutableLiveData<List<Item>>()
    val repositories: LiveData<List<Item>> = _repositories

    private var searchJob: Job? = null

    private val _search: LiveData<List<RepositorySearch>> = roomRepository.getSearch()
    val search: LiveData<List<RepositorySearch>> get() = _search

    fun search(userInput: String) {
        searchJob?.cancel() // cancel previous job when user enters new letter
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(300)      // add some delay before search, this function checks if coroutine is canceled, if it is canceled it won't continue execution
            val listOfItems = repository.getRepo(userInput)
            if (!listOfItems.data?.items.isNullOrEmpty()){
                _repositories.postValue(listOfItems.data?.items!!)
                val search = RepositorySearch(userInput)
                if (roomRepository.searchExists(userInput)){

                }else{
                    if (userInput.length > 2)run {
                        roomRepository.addSearch(search)
                    }
                }
            }
        }
    }




}