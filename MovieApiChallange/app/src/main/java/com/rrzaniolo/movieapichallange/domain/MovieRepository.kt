package com.rrzaniolo.movieapichallange.domain

import com.rrzaniolo.movieapichallange.data.models.GetMovieResponse
import com.rrzaniolo.movieapichallange.data.models.MovieResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */
interface MovieRepository {

    /**
     * Get the movies list from the api.
     *
     * @return a list of MovieResponse**/
    fun getMovies(): Flowable<GetMovieResponse>

    /**
     * Save a movie locally.
     *
     * @param movieResponse the movie to be saved**/
    fun saveMovie(movieResponse: MovieResponse): Completable

    /**
     * Get a movie that was locally saved by its Id.
     *
     * @param id the id of the movie.
     * @return A MovieResponse
     * **/
    fun getMovieById(id: Int?): Single<MovieResponse>

    /**
     * Delete a movies that was locally saved.
     *
     * @param movieResponse the movie to be deleted.
     * **/
    fun deleteMovie(movieResponse: MovieResponse): Completable

    /**
     * Get a list with all the movies that have been locally saved.
     *
     * @return a list of MovieResponse**/
    fun getMoviesLocally(): Flowable<List<MovieResponse>>
}