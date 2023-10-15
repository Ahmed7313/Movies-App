package com.example.moviesapp.VIewModelsTests

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails
import com.example.moviesapp.domain.usecase.GetMovieDetailsUseCase
import com.example.moviesapp.ui.screens.MovieDetailsScreen.MovieDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieDetailsViewModel
    private val mockGetMovieDetails: GetMovieDetailsUseCase = mockk()

    private val mockMovieDetails = MovieDetails(/* populate with appropriate data */)

    @Before
    fun setUp() {
        viewModel = MovieDetailsViewModel(mockGetMovieDetails)
    }

    @Test
    fun `fetchMovieDetails - success`() = runBlockingTest {
        val movieId = 1

        coEvery { mockGetMovieDetails.execute(movieId) } returns flowOf(Resource.Success(mockMovieDetails))

        viewModel.fetchMovieDetails(movieId)

        assertEquals(Resource.Success(mockMovieDetails), viewModel.movieDetails.value)
    }

    @Test
    fun `fetchMovieDetails - error`() = runBlockingTest {
        val movieId = 1

        coEvery { mockGetMovieDetails.execute(movieId) } returns flowOf(Resource.Error("Some error"))

        viewModel.fetchMovieDetails(movieId)

        assertEquals(Resource.Error<MovieDetails>("Some error"), viewModel.movieDetails.value)
    }

    @Test
    fun `fetchMovieDetails - loading`() = runBlockingTest {
        val movieId = 1

        coEvery { mockGetMovieDetails.execute(movieId) } returns flowOf(Resource.Loading())

        viewModel.fetchMovieDetails(movieId)

        assertEquals(Resource.Loading<MovieDetails>(), viewModel.movieDetails.value)
    }
}