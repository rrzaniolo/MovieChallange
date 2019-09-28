package com.rrzaniolo.movieapichallange.di.configurations

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rrzaniolo.movieapichallange.data.daos.MovieDao
import com.rrzaniolo.movieapichallange.data.models.MovieResponse

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
@Database(entities = [MovieResponse::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}