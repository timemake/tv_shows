package com.android.tvshowsapp.data

import androidx.paging.PagingSource
import com.android.tvshowsapp.data.model.Show
import com.android.tvshowsapp.network.TVApi
import retrofit2.HttpException
import java.io.IOException

private const val SHOW_STARTING_PAGE_INDEX = 1
private const val ITEM_NUMBERS_IN_PAGE = 20

class TVSearchPagingSource(
        private val tvApi: TVApi,
        private val query: String
) : PagingSource<Int, Show>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        val position = params.key ?: SHOW_STARTING_PAGE_INDEX
        return try{
            val response = tvApi.getSearchShows(query, position)
            val shows = response.tv_shows

            LoadResult.Page(
                    data = shows,
                    prevKey = if(position == SHOW_STARTING_PAGE_INDEX) null else position -1,
                    nextKey = if(shows.size < ITEM_NUMBERS_IN_PAGE) null else position + 1
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}