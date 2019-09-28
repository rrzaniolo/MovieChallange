package com.rrzaniolo.movieapichallenge.di.modules

import com.rrzaniolo.movieapichallenge.di.configurations.provideDataBaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
internal val dataBaseModule = module {
    single { provideDataBaseBuilder(androidContext()) }
}