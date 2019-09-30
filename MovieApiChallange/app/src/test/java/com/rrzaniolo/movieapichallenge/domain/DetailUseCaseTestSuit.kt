package com.rrzaniolo.movieapichallenge.domain

import com.rrzaniolo.movieapichallenge.domain.repositories.GenreRepository
import com.rrzaniolo.movieapichallenge.domain.repositories.MovieRepository
import com.rrzaniolo.movieapichallenge.domain.usecase.DetailUseCase
import com.rrzaniolo.movieapichallenge.mockedMovieResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/30/2019.
 * All rights reserved.
 */
class DetailUseCaseTestSuit: AutoCloseKoinTest() {

    lateinit var movieRepository: MovieRepository
    lateinit var genreRepository: GenreRepository

    lateinit var useCase: DetailUseCase

    @Before
    fun setUp(){
        movieRepository = mock(MovieRepository::class.java)
        genreRepository = mock(GenreRepository::class.java)

        useCase = DetailUseCase(movieRepository)
    }

    @Test
    fun `Assert saveMovie success`(){
        `when`(movieRepository.saveMovie(mockedMovieResponse)).thenReturn(Completable.complete())

        val response = useCase.saveMovie(mockedMovieResponse)

        assertNotNull(response)
    }

    @Test
    fun `Assert saveMovie error`(){
        `when`(movieRepository.getMovies()).thenReturn(Flowable.error(Throwable()))

        val response = useCase.saveMovie(mockedMovieResponse)

        assertNull(response)
    }

    @Test
    fun `Assert removeMovie success`(){
        `when`(movieRepository.saveMovie(mockedMovieResponse)).thenReturn(Completable.complete())

        val response = useCase.removeMovie(mockedMovieResponse)

        assertNull(response)
    }

    @Test
    fun `Assert removeMovie error`(){
        `when`(movieRepository.getMovies()).thenReturn(Flowable.error(Throwable()))

        val response = useCase.removeMovie(mockedMovieResponse)

        assertNull(response)
    }

    @Test
    fun `Assert getMovie success`(){
        `when`(movieRepository.getMovieById(mockedMovieResponse.id)).thenReturn(Single.just(mockedMovieResponse))

        val response = useCase.removeMovie(mockedMovieResponse)

        assertNull(response)
    }

    @Test
    fun `Assert getMovie error`(){
        `when`(movieRepository.getMovieById(mockedMovieResponse.id)).thenReturn(Single.error(Throwable()))

        val response = useCase.getMovie(mockedMovieResponse)

        assertNotNull(response)
    }
}