package com.example.moviesapp.UseCaseTests

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
import androidx.paging.PagingData
import com.example.moviesapp.data.NetworkChecker.NetworkChecker
import com.example.moviesapp.domain.Model.Movie
import com.example.moviesapp.domain.repos.IMoviesRepository
import com.example.moviesapp.ui.utils.toPagingData
import com.softaai.mvvmdemo.domain.usecase.GetPopularMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private lateinit var useCase: GetPopularMoviesUseCase
    private lateinit var mockRepository: IMoviesRepository
    private lateinit var mockNetworkChecker: NetworkChecker

    @Before
    fun setUp() {
        mockRepository = mockk()
        mockNetworkChecker = mockk()
        useCase = GetPopularMoviesUseCase(mockRepository, mockNetworkChecker)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when network is available, fetches movies from API`() = runBlockingTest {
        val mockedPagingData = mockk<PagingData<Movie>>() // mock the PagingData object

        // Setup conditions for the test
        coEvery { mockNetworkChecker.isNetworkAvailable() } returns true
        coEvery { mockRepository.getMoviesStream("en", "US") } returns flowOf(mockedPagingData)

        // Execute the use case function
        val result = useCase.execute("en", "US").first()

        // Check the results
        assertEquals(result, mockedPagingData)
    }

    @Test
    fun `when network is unavailable, fetches movies from database`() = runBlockingTest {
        val mockedMoviesList = listOf(mockk<Movie>())
        val mockedPagingData = mockedMoviesList.toPagingData()

        // Setup conditions for the test
        coEvery { mockNetworkChecker.isNetworkAvailable() } returns false
        coEvery { mockRepository.getMoviesFromDatabase() } returns mockedMoviesList

        // Execute the use case function
        val result = useCase.execute("en", "US").first()

        // Check the results
        assertEquals(result, mockedPagingData)
    }
}
