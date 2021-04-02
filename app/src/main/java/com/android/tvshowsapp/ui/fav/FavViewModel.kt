package com.android.tvshowsapp.ui.fav

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.tvshowsapp.database.FavShow
import com.android.tvshowsapp.database.FavShowRepository
import kotlinx.coroutines.launch

class FavViewModel @ViewModelInject constructor(private val repository: FavShowRepository): ViewModel() {

    val shows = repository.getFavoriteShows()

    fun insert(favShow: FavShow){
        viewModelScope.launch {
            repository.insert(favShow)
        }
    }

    fun delete(favShow: FavShow){
        viewModelScope.launch {
            repository.delete(favShow)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}