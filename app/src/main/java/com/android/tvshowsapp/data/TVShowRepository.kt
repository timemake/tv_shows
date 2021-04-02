package com.android.tvshowsapp.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.android.tvshowsapp.network.TVApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowRepository @Inject constructor(private val tvApi: TVApi) {

    fun getPopularShows() =
            Pager(
                    config = PagingConfig(
                            pageSize = 5,
                            enablePlaceholders = false
                    ),
                    pagingSourceFactory = {
                        TVShowPagingSource(tvApi)
                    }
            ).liveData

    fun getSearchShows(query: String) =
            Pager(
                    config = PagingConfig(
                            pageSize = 5,
                            enablePlaceholders = false
                    ),
                    pagingSourceFactory = {
                        TVSearchPagingSource(tvApi, query)
                    }
            ).liveData
}