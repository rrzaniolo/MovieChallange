package com.rrzaniolo.movieapichallange

import android.app.Application
import com.rrzaniolo.movieapichallange.di.netWorkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */
class MovieApiChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MovieApiChallengeApplication)
            modules(listOf(netWorkModule))
        }
    }
}