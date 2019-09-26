package com.rrzaniolo.movieapichallange.data.repositories

import com.rrzaniolo.movieapichallange.BuildConfig
import com.rrzaniolo.movieapichallange.data.apis.MovieApi
import com.rrzaniolo.movieapichallange.data.models.GetMovieResponse
import com.rrzaniolo.movieapichallange.data.models.MovieResponse
import com.rrzaniolo.movieapichallange.domain.MovieRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */
class MovieRepositoryImp(private val movieApi: MovieApi): MovieRepository {
    override fun getMovies(): Flowable<GetMovieResponse> {
        return movieApi
            .getMovies(BuildConfig.API_KEY)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
    }

    override fun saveMovie(movieResponse: MovieResponse): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieById(id: Int?): Single<MovieResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMovie(movieResponse: MovieResponse): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMoviesLocally(): Flowable<List<MovieResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}