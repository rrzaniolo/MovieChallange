package com.rrzaniolo.movieapichallenge.domain.usecase

import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.domain.repositories.MovieRepository
import io.reactivex.Flowable

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/29/2019.
 * All rights reserved.
 */
class FavoriteUseCase (private val movieRepository: MovieRepository) {

    fun getFavorites(): Flowable<List<MovieResponse>> {
        return movieRepository.getMoviesLocally()
    }
}