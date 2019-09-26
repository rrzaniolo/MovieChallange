package com.rrzaniolo.movieapichallange.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rrzaniolo.movieapichallange.data.models.MovieResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */
@Dao
interface MovieDao {

    /**
     * Save a movie locally.
     *
     * @param movieResponse the movie to be saved.
     * **/
    @Insert fun saveMovie(movieResponse: MovieResponse): Completable

    /**
     * Get a movie that was locally saved by it's id.
     *
     * @param id the movie id.
     * @return a MovieResponse
     * **/
    @Query("SELECT * FROM movieresponse WHERE id = :id") fun getMovieById(id: Int?): Single<MovieResponse>

    /**
     * Delete a movie tha has been locally saved
     *
     * @param movieResponse the movie to be deleted.
     * **/
    @Delete fun deleteMovie(movieResponse: MovieResponse): Completable

    /**
     * Gets a list of all the locally saved movies
     *
     * @return a list of MovieResponse.
     * **/
    @Query("SELECT * FROM movieresponse") fun getMovies(): Flowable<List<MovieResponse>>

}