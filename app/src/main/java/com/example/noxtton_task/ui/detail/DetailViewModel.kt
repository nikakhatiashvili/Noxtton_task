package com.example.noxtton_task.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noxtton_task.model.Item
import com.example.noxtton_task.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val roomRepository: RoomRepository) :
    ViewModel() {
    private var _repositories = MutableLiveData<List<Item>>()
    val repositories: LiveData<List<Item>> = _repositories

    private var _exisist = MutableLiveData<Boolean>()
    val exisist: LiveData<Boolean> = _exisist



//    fun readAllItems() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val data = roomRepository.favoriteItems()
//            _repositories.postValue(data.)
//        }
//    }
    private val _readData: LiveData<List<Item>> = roomRepository.favoriteItems()
    val readData: LiveData<List<Item>> get() = _readData
    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO)  {
            roomRepository.deleteItem(id)
        }
    }

    fun exists(id: Int) {
       viewModelScope.launch(Dispatchers.IO) {
         _exisist.postValue(roomRepository.exists(id))
       }
    }


fun addItem(item: Item) {
    viewModelScope.launch(Dispatchers.IO)  {
        roomRepository.addItem(item)
    }
    }
}