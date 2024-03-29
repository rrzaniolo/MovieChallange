package com.rrzaniolo.movieapichallenge.data.repositories

import com.rrzaniolo.movieapichallenge.BuildConfig
import com.rrzaniolo.movieapichallenge.data.apis.MovieApi
import com.rrzaniolo.movieapichallenge.data.models.GetMovieResponse
import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.di.configurations.Database
import com.rrzaniolo.movieapichallenge.domain.repositories.MovieRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */
class MovieRepositoryImp(private val movieApi: MovieApi, private val dataBase: Database):
    MovieRepository {
    override fun getMovies(): Flowable<GetMovieResponse> {
        return movieApi
            .getMovies(BuildConfig.API_KEY)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
    }

    override fun saveMovie(movieResponse: MovieResponse): Completable {
        return dataBase
            .movieDao()
            .saveMovie(movieResponse)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

    override fun getMovieById(id: Int?): Single<MovieResponse> {
        return dataBase
            .movieDao()
            .getMovieById(id)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

    override fun deleteMovie(movieResponse: MovieResponse): Completable {
        return dataBase
            .movieDao()
            .deleteMovie(movieResponse)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
    }

    override fun getMoviesLocally(): Flowable<List<MovieResponse>> {
        return dataBase
            .movieDao()
            .getMovies()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
    }
}