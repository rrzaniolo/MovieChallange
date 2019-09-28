package com.rrzaniolo.movieapichallenge.di.configurations

import android.content.Context
import androidx.room.Room

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
fun provideDataBaseBuilder(context: Context) = Room.databaseBuilder(
    context,
    Database::class.java,
    "movieApiChallenge.db"
).build()