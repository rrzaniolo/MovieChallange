package com.rrzaniolo.movieapichallenge.data.repositories

import com.rrzaniolo.movieapichallenge.BuildConfig
import com.rrzaniolo.movieapichallenge.data.apis.MovieApi
import com.rrzaniolo.movieapichallenge.data.models.GenreResponse
import com.rrzaniolo.movieapichallenge.domain.repositories.GenreRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class GenreRepositoryImp (private val api: MovieApi) : GenreRepository {

    override fun getGenres(): Flowable<GenreResponse> {
        return api
            .loadGenres(BuildConfig.API_KEY)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
    }
}