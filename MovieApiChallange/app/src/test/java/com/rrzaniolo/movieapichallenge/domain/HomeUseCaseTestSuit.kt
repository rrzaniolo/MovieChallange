package com.rrzaniolo.movieapichallenge.domain

import com.rrzaniolo.movieapichallenge.domain.repositories.GenreRepository
import com.rrzaniolo.movieapichallenge.domain.repositories.MovieRepository
import com.rrzaniolo.movieapichallenge.domain.usecase.HomeUseCase
import com.rrzaniolo.movieapichallenge.mockedGenreResponse
import com.rrzaniolo.movieapichallenge.mockedGetMovieResponse
import io.reactivex.Flowable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/30/2019.
 * All rights reserved.
 */
class HomeUseCaseTestSuit: AutoCloseKoinTest() {

    lateinit var movieRepository: MovieRepository
    lateinit var genreRepository: GenreRepository

    lateinit var useCase: HomeUseCase

    @Before
    fun setUp(){
        movieRepository = mock(MovieRepository::class.java)
        genreRepository = mock(GenreRepository::class.java)

        useCase = HomeUseCase(movieRepository, genreRepository)
    }

    @Test
    fun `Assert getGenres success`(){
        `when`(genreRepository.getGenres()).thenReturn(Flowable.just(mockedGenreResponse))

        val response = useCase.getGenres()

        assertNotNull(response.firstOrError())
    }

    @Test
    fun `Assert getGenres error`(){
        `when`(genreRepository.getGenres()).thenReturn(Flowable.error(Throwable()))

        val response = useCase.getGenres()

        assertNotNull(response.firstElement())
    }

    @Test
    fun `Assert getMovies success`(){
        `when`(movieRepository.getMovies()).thenReturn(Flowable.just(mockedGetMovieResponse))

        val response = useCase.getMovies()

        assertNotNull(response.firstOrError())
    }

    @Test
    fun `Assert getMovies error`(){
        `when`(movieRepository.getMovies()).thenReturn(Flowable.error(Throwable()))

        val response = useCase.getMovies()

        assertNotNull(response.firstElement())
    }
}