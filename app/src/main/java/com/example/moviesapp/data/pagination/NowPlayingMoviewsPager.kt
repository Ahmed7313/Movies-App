package com.example.moviesapp.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.data.remote.MovieApiService
import com.example.moviesapp.data.remote.dto.PopularPlayingMovies.MovieDTO
import com.example.moviesapp.data.repos.MoviesRepositoryImp

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
//class NowPlayingMoviesPager (
//    val service : MovieApiService,
//    val language: String,  // Inject language
//    val region: String
//): PagingSource<Int, MovieDTO>() {
//    override fun getRefreshKey(state: PagingState<Int, MovieDTO>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {
//        return try {
//            val page = params.key ?: 1
//            val response = service.getPopularMovies(page = page, isoCode = language, region = region)
//
//            LoadResult.Page(
//                data = response.results?: emptyList(),
//                prevKey = if (page == 1) null else page.minus(1),
//                nextKey = if (response.results.isEmpty()) null else page.plus(1),
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}