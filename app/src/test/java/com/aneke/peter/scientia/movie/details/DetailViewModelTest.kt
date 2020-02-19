package com.aneke.peter.scientia.movie.details


import com.aneke.peter.scientia.models.Movie
import com.aneke.peter.scientia.movie.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class DetailViewModelTest : KoinTest {

    private lateinit var detailViewModel: DetailViewModel

    @Mock
    private lateinit var repository: MovieRepository

    val id = 8


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        detailViewModel = DetailViewModel(repository)

    }


    @ExperimentalCoroutinesApi
    @Test
    fun testThatMovieLiveDataValueIsNotNullAfterCallToFetchMovieDetails() = runBlockingTest {
        Mockito.`when`(repository.fetchMovie(id)).thenReturn(
            Movie()
        ).then {
            assertNotNull(detailViewModel.movie.value)
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testThatMovieLiveDataValueIsNullAfterCallToFetchMovieDetailsReturnsNull() =
        runBlockingTest {
            Mockito.`when`(repository.fetchMovie(id)).thenReturn(
                null
            ).then {
                assertNull(detailViewModel.movie.value)
            }
        }


    @ExperimentalCoroutinesApi
    @Test
    fun testThatMovieDetailsLiveDataValuesAreNullAfterCallToFetchMovieDetailsReturnsNull() =
        runBlockingTest {
            Mockito.`when`(repository.fetchMovie(id)).thenReturn(
                null
            ).then {
                assertNull(detailViewModel.movieBackdrop.value)
                assertNull(detailViewModel.movieDesc.value)
                assertNull(detailViewModel.moviePoster.value)
                assertNull(detailViewModel.movieReleaseDate.value)
                assertNull(detailViewModel.movieRating.value)
                assertNull(detailViewModel.movieTitle.value)
            }
        }


    @ExperimentalCoroutinesApi
    @Test
    fun testThatMovieDetailsLiveDataValuesAreUpdatedCorrectlyAfterCallToFetchMovieDetailsReturnAAvalid() =
        runBlockingTest {
            Mockito.`when`(repository.fetchMovie(id)).thenReturn(
                Movie(
                    backdropPath = "backdropurl",
                    overview = "Movie overview",
                    posterPath = "Poster path",
                    releaseDate = "Date of release",
                    voteAverage = 90.0,
                    title = "Game of thrones"

                )
            ).then {
                assertEquals(detailViewModel.movieBackdrop.value, "backdropurl")
                assertNull(detailViewModel.movieDesc.value, "Movie overview")
                assertNull(detailViewModel.moviePoster.value, "Poster path")
                assertNull(detailViewModel.movieReleaseDate.value, "Date of release")
                assertNull(detailViewModel.movieRating.value, 90.0)
                assertNull(detailViewModel.movieTitle.value, "Game of thrones" )
            }
        }


}