package com.rrzaniolo.movieapichallenge.domain.usecase

import com.rrzaniolo.movieapichallenge.data.models.GenreResponse
import com.rrzaniolo.movieapichallenge.data.models.GetMovieResponse
import com.rrzaniolo.movieapichallenge.domain.repositories.GenreRepository
import com.rrzaniolo.movieapichallenge.domain.repositories.MovieRepository
import io.reactivex.Flowable

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class HomeUseCase(private val movieRepository: MovieRepository, private val genreRepository: GenreRepository) {

    fun getMovies(): Flowable<GetMovieResponse> = movieRepository.getMovies()

    fun getGenres(): Flowable<GenreResponse> = genreRepository.getGenres()
}