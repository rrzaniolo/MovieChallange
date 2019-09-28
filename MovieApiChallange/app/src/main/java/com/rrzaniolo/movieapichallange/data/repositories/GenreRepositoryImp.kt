package com.rrzaniolo.movieapichallange.data.repositories

import com.rrzaniolo.movieapichallange.BuildConfig
import com.rrzaniolo.movieapichallange.data.apis.MovieApi
import com.rrzaniolo.movieapichallange.data.models.GenreResponse
import com.rrzaniolo.movieapichallange.domain.repositories.GenreRepository
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