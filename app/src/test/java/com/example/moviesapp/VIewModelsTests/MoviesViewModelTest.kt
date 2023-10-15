package com.example.moviesapp.VIewModelsTests

import androidx.paging.PagingData
import com.example.moviesapp.data.NetworkChecker.NetworkChecker
import com.example.moviesapp.data.local.db.Config.AppConfigEntity
import com.example.moviesapp.domain.Model.Movie
import com.example.moviesapp.domain.repos.IMoviesRepository
import com.example.moviesapp.domain.usecase.GetAppConfigUseCase
import com.example.moviesapp.ui.screens.MoviesScreen.MoviesViewModel
import com.softaai.mvvmdemo.domain.usecase.GetPopularMoviesUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals


/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel
    private val mockGetPopularMovies: GetPopularMoviesUseCase = mockk()
    private val mockGetAppConfig: GetAppConfigUseCase = mockk()
    private val mockNetworkChecker: NetworkChecker = mockk()
    private val mockRepository: IMoviesRepository = mockk()

    private val mockAppConfig: AppConfigEntity = AppConfigEntity(language = "en-US", region = "US")
    private val mockMovie: Movie = Movie(id = 1, title = "Sample Movie")
    private val pagingData = PagingData.from(listOf(mockMovie))

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)  // Set main dispatcher to unconfined for testing

        coEvery { mockGetAppConfig.execute() } returns flowOf(mockAppConfig)
        every { mockNetworkChecker.isNetworkAvailable() } returns true
        coEvery { mockRepository.getMoviesStream(any(), any()) } returns flowOf(pagingData)
        viewModel = MoviesViewModel(mockGetPopularMovies, mockGetAppConfig)

        coEvery { mockGetPopularMovies.execute("en-US", "US") } returns flowOf(pagingData)

    }

    @Test
    fun testGetMoviesFlow_networkAvailable() = runBlocking {
        val moviesFlow = viewModel.getMoviesFlow()

        // Collect the results from the flow
        val resultList = mutableListOf<PagingData<Movie>>()
        moviesFlow.collect { data ->
            resultList.add(data)
        }

        assertEquals(mockMovie, resultList[0]) // Assert that the result contains the mockMovie
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
