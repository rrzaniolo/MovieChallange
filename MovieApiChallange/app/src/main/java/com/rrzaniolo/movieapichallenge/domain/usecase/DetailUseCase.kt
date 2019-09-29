package com.rrzaniolo.movieapichallenge.domain.usecase

import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.domain.repositories.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class DetailUseCase (private val movieRepository: MovieRepository) {

    fun saveMovie(movie: MovieResponse): Completable {
        return movieRepository.saveMovie(movie)
    }

    fun getMovie(movie: MovieResponse): Single<MovieResponse> {
        return movieRepository.getMovieById(movie.id)
    }

    fun removeMovie(movie: MovieResponse): Completable {
        return movieRepository.deleteMovie(movie)
    }
}