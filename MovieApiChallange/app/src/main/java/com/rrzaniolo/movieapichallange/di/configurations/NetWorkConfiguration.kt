package com.rrzaniolo.movieapichallange.di.configurations

import com.rrzaniolo.movieapichallange.BuildConfig
import com.rrzaniolo.movieapichallange.data.apis.MovieApi
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/25/2019.
 * All rights reserved.
 */

fun provideOkHttpClientBuilder(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
    val builder = OkHttpClient.Builder()
    interceptors.forEach { builder.addInterceptor(it) }
    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        builder.addInterceptor(httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        })
    }
    return builder.build()
}

fun provideInterceptor(): Set<@JvmSuppressWildcards Interceptor> = setOf(Interceptor { chain ->
    val request = chain.request()

    chain.proceed(request)
})

fun provideMoshi(): Moshi = Moshi.Builder().build()

fun provideMovieApi():
        MovieApi {
    return Retrofit.Builder()
        .client(provideOkHttpClientBuilder(provideInterceptor()))
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .build()
        .create(MovieApi::class.java)
}