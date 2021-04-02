package com.android.tvshowsapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.tvshowsapp.database.FavShow
import com.android.tvshowsapp.database.FavShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(private val repository: FavShowRepository) : ViewModel() {

    fun addToFavorite(favShow: FavShow){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(favShow)
        }
    }

    suspend fun checkShow(id: String) = repository.checkShow(id)

    fun removeFromFavorite(id: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.removeFromFavorite(id)
        }
    }
}