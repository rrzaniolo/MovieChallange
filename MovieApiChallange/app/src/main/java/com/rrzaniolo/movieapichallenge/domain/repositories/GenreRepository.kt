package com.rrzaniolo.movieapichallenge.domain.repositories

import com.rrzaniolo.movieapichallenge.data.models.GenreResponse
import io.reactivex.Flowable

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
interface GenreRepository {

    fun getGenres(): Flowable<GenreResponse>
}