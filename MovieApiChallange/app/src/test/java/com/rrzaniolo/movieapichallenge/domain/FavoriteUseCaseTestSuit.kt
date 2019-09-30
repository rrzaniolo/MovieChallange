package com.rrzaniolo.movieapichallenge.domain

import com.rrzaniolo.movieapichallenge.domain.repositories.MovieRepository
import com.rrzaniolo.movieapichallenge.domain.usecase.FavoriteUseCase
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
class FavoriteUseCaseTestSuit: AutoCloseKoinTest() {

    lateinit var movieRepository: MovieRepository

    lateinit var useCase: FavoriteUseCase

    @Before
    fun setUp(){
        movieRepository = mock(MovieRepository::class.java)

        useCase = FavoriteUseCase(movieRepository)
    }

    @Test
    fun `Assert getMovies success`(){
        `when`(movieRepository.getMoviesLocally()).thenReturn(Flowable.just(ArrayList()))

        val response = useCase.getFavorites()

        assertNotNull(response.firstOrError())
    }

    @Test
    fun `Assert getMovies error`(){
        `when`(movieRepository.getMoviesLocally()).thenReturn(Flowable.error(Throwable()))

        val response = useCase.getFavorites()

        assertNotNull(response.firstElement())
    }
}