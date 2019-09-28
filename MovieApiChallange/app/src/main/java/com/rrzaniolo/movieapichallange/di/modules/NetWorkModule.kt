package com.rrzaniolo.movieapichallange.di.modules

import com.rrzaniolo.movieapichallange.di.configurations.provideMovieApi
import org.koin.dsl.module

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */

val netWorkModule = module {
    single { provideMovieApi() }
}