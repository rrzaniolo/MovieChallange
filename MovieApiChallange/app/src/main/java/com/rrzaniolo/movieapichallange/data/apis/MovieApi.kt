package com.rrzaniolo.movieapichallange.data.apis

import com.rrzaniolo.movieapichallange.data.models.GenreResponse
import com.rrzaniolo.movieapichallange.data.models.GetMovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */
interface MovieApi {

    @GET("movie/upcoming")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ):Flowable<GetMovieResponse>

    @GET("genre/movie/list")
    fun loadGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): Flowable<GenreResponse>
}