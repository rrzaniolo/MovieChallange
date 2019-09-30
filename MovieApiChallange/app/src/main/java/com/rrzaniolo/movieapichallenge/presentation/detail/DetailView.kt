package com.rrzaniolo.movieapichallenge.presentation.detail

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.rrzaniolo.movieapichallenge.BuildConfig
import com.rrzaniolo.movieapichallenge.R
import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.di.modules.loadDetailModule
import com.rrzaniolo.movieapichallenge.presentation.base.BaseView
import com.rrzaniolo.movieapichallenge.presentation.base.isVisible
import com.rrzaniolo.movieapichallenge.presentation.base.toDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class DetailView: BaseView() {
    private val viewModel: DetailViewModel by viewModel()

    private var movieData: MovieResponse? = null

    companion object{
        const val MOVIE_DATA = "movieData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_detail)
        init()
    }

    private fun init() {
        loadDetailModule()
        movieData = intent.getParcelableExtra(MOVIE_DATA)
        setupToolbar()
        initViewModel()
        initListeners()
        viewModel.getMovieData(movieData)
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = movieData?.title
    }

    private fun initListeners(){
        movieLike.setOnClickListener {
            likeOrUnlikeMovie()
        }
    }

    private fun initViewModel(){
        viewModel.detailViewState.observe(this, Observer{ viewState ->
            hideLoading()
            when(viewState){
                is DetailViewStates.SaveSuccess -> {
                    setLikeIconColor(true)
                    Toast.makeText(this, R.string.like_success, Toast.LENGTH_SHORT).show()
                }
                is DetailViewStates.SaveError -> {
                    setLikeIconColor(false)
                    Toast.makeText(this, R.string.like_error, Toast.LENGTH_SHORT).show()
                }
                is DetailViewStates.ShowLoading -> {
                    showLoading()
                }
                is DetailViewStates.RemoveSuccess -> {
                    setLikeIconColor(false)
                    Toast.makeText(this, R.string.unlike_success, Toast.LENGTH_SHORT).show()
                }
                is DetailViewStates.RemoveError -> {
                   setLikeIconColor(true)
                    Toast.makeText(this, R.string.unlike_error, Toast.LENGTH_SHORT).show()
                }
                is DetailViewStates.GetMovieSuccess -> {
                    displayMovieData(viewState.movie)
                }
                is DetailViewStates.GetMovieError -> {
                    Toast.makeText(this, R.string.load_error, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun setLikeIconColor(isLiked: Boolean){
        movieLike.setColorFilter(
            ContextCompat.getColor(
                this,
                if(isLiked) R.color.colorPrimaryDark else R.color.white
            ), android.graphics.PorterDuff.Mode.SRC_IN)

    }

    private fun showLoading(){
        detailLoading.isVisible(true)
    }

    private fun hideLoading(){
        detailLoading.isVisible(false)
    }

    private fun displayMovieData(movieData: MovieResponse){
        with(movieData) {
            movieDetail.text = if(this.overview.isNullOrEmpty()) getString(R.string.no_overview) else this.overview

            movieRate.text = this.voteAverage.toString()

            movieDate.text = this.releaseDate?.toDate()

            Picasso.get().load(BuildConfig.IMAGE_URL + this.backdropPath).into(detailBanner)

            setLikeIconColor(isLiked)
        }
    }

    private fun likeOrUnlikeMovie(){
        movieData?.let {
            it.isLiked = !it.isLiked
            viewModel.saveOrRemoveMovie(it)
        }
    }
}