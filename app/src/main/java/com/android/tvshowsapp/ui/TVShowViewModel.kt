package com.android.tvshowsapp.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.android.tvshowsapp.data.TVShowRepository
import com.android.tvshowsapp.data.model.Show

class TVShowViewModel @ViewModelInject constructor(
        private val repository: TVShowRepository) : ViewModel() {

            val mostPopluarShows = repository.getPopularShows().cachedIn(viewModelScope)

    val searchText = MutableLiveData<String>()

    val searchShows = Transformations.switchMap(searchText){ text ->
        repository.getSearchShows(text).cachedIn(viewModelScope)
    }

    fun getSearchShows(text: String){
        searchText.value = text
    }

}