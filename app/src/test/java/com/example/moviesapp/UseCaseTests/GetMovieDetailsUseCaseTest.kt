package com.example.moviesapp.UseCaseTests

import com.example.moviesapp.data.local.db.Config.AppConfigDao
import com.example.moviesapp.data.local.db.Config.AppConfigEntity
import com.example.moviesapp.data.local.db.MovieDetails.MovieDetailsEntity
import com.example.moviesapp.data.local.db.MovieDetails.toDomainModel
import com.example.moviesapp.data.remote.dto.MovieDetailsDTO.Genre
import com.example.moviesapp.data.remote.dto.MovieDetailsDTO.ProductionCompany
import com.example.moviesapp.data.remote.dto.MovieDetailsDTO.ProductionCountry
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails
import com.example.moviesapp.domain.Model.MovieDetails.SpokenLanguage
import com.example.moviesapp.domain.repos.IMovieDetailsRepository
import com.example.moviesapp.domain.usecase.GetMovieDetailsUseCase
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList

class GetMovieDetailsUseCaseTest {


    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    val listGenreType = Types.newParameterizedType(List::class.java, Genre::class.java)
    val genreAdapter: JsonAdapter<List<Genre>> = moshi.adapter(listGenreType)

    val genresJson = genreAdapter.toJson(listOf(
        Genre(id = 1, name = "Action"),
        Genre(id = 2, name = "Adventure")
    ))

    val listProductionCompanyType = Types.newParameterizedType(List::class.java, ProductionCompany::class.java)
    val productionCompanyAdapter: JsonAdapter<List<ProductionCompany>> = moshi.adapter(listProductionCompanyType)

    val productionCompaniesJson = productionCompanyAdapter.toJson(listOf(
        ProductionCompany(id = 1, name = "Sample Production 1", logoPath = "/sampleLogo1.jpg"),
        ProductionCompany(id = 2, name = "Sample Production 2", logoPath = "/sampleLogo2.jpg")
    ))

    val listProductionCountryType = Types.newParameterizedType(List::class.java, ProductionCountry::class.java)
    val productionCountryAdapter: JsonAdapter<List<ProductionCountry>> = moshi.adapter(listProductionCountryType)

    val productionCountriesJson = productionCountryAdapter.toJson(listOf(
        ProductionCountry(iso31661 = "US", name = "United States"),
        ProductionCountry(iso31661 = "UK", name = "United Kingdom")
    ))


    @MockK
    lateinit var movieDetailsRepository: IMovieDetailsRepository

    @MockK
    lateinit var configDao: AppConfigDao

    private lateinit var useCase: GetMovieDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetMovieDetailsUseCase(movieDetailsRepository, configDao)
    }

    @Test
    fun `when movie details are fetched successfully, returns Resource Success`() = runBlockingTest {
        // Given
        val movieId = 123
        val expectedMovieDetailsEntity = MovieDetailsEntity(  id = 100,
            adult = false,
            backdropPath = "/sampleBackdropPath.jpg",
            budget = 1000000,
            genres = genresJson,
            homepage = "https://samplemovie.com",
            imdbId = "tt1234567",
            originalLanguage = "en",
            originalTitle = "Sample Movie",
            overview = "This is a sample movie for testing purposes.",
            popularity = 100.0,
            posterPath = "/samplePosterPath.jpg",
            productionCompanies = productionCompaniesJson,
            productionCountries = productionCountriesJson,
            releaseDate = "2022-01-01",
            revenue = 2000000,
            runtime = 120,
            spokenLanguages = "TODO", // Convert your spokenLanguages list to JSON like above
            status = "Released",
            tagline = "This is a sample tagline.",
            title = "Sample Movie Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 5000)
        val expectedMovieDetails = expectedMovieDetailsEntity.toDomainModel()

        coEvery { configDao.getAppConfig() } returns AppConfigEntity(language = "en-US", region = "US")
        coEvery { movieDetailsRepository.getMovieDetails(movieId, "en-US") } returns flowOf(Resource.Success(expectedMovieDetailsEntity))

        // When
        val result = useCase.execute(movieId).toList()

        // Then
        assertEquals(listOf(Resource.Loading<MovieDetails>(), Resource.Success(expectedMovieDetails)), result)
    }

    @Test
    fun `when an error occurs while fetching movie details, returns Resource Error`() = runBlockingTest {
        // Given
        val movieId = 123
        val errorMessage = "Network error"

        coEvery { configDao.getAppConfig() } returns AppConfigEntity(language = "en-US", region = "US")
        coEvery { movieDetailsRepository.getMovieDetails(movieId, "en-US") } returns flowOf(Resource.Error<MovieDetailsEntity>(errorMessage))

        // When
        val result = useCase.execute(movieId).toList()

        // Then
        assertEquals(listOf(Resource.Loading<MovieDetails>(), Resource.Error<MovieDetails>(errorMessage)), result)
    }
}
