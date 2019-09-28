package com.rrzaniolo.movieapichallenge.di.modules

import com.rrzaniolo.movieapichallenge.di.configurations.provideMovieApi
import org.koin.dsl.module

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */

val netWorkModule = module {
    single { provideMovieApi() }
}