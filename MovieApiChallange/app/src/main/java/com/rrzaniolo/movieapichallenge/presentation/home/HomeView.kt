package com.rrzaniolo.movieapichallenge.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrzaniolo.movieapichallenge.R
import com.rrzaniolo.movieapichallenge.data.models.GenreResponse
import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.di.modules.loadHomeModule
import com.rrzaniolo.movieapichallenge.presentation.MovieListAdapter
import com.rrzaniolo.movieapichallenge.presentation.base.BaseRecyclerAdapter
import com.rrzaniolo.movieapichallenge.presentation.base.isVisible
import com.rrzaniolo.movieapichallenge.presentation.base.showErrorDialog
import kotlinx.android.synthetic.main.view_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class HomeView : Fragment(), BaseRecyclerAdapter.OnItemClickListener{

    private val viewModel: HomeViewModel by viewModel()
    private val movieListAdapter by lazy { MovieListAdapter() }

    companion object {
        private lateinit var instance: HomeView

        fun newInstance(): HomeView {
            return if(::instance.isInitialized) instance else HomeView()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init(){
        loadHomeModule()
        initViewModel()
        setUpRecyclerView()
        viewModel.loadMovies()
    }

    private fun initViewModel(){
        viewModel.homeViewState.observe(this, Observer{viewState ->
            when(viewState){
                is HomeViewState.ShowSuccess -> showSuccess(viewState.movies, viewState.genres)
                is HomeViewState.ShowError -> showError()
                is HomeViewState.ShowLoading -> showLoading()
                is HomeViewState.HideLoading -> hideLoading()
            }
        })
    }

    private fun setUpRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL

        movieListAdapter.listener = this

        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.itemAnimator = DefaultItemAnimator()
        homeRecyclerView.adapter = movieListAdapter
    }

    private fun showSuccess(movies: List<MovieResponse>, genres: GenreResponse) {
        homeRecyclerView.visibility = View.VISIBLE
        movieListAdapter.genreList = genres
        movieListAdapter.setList(movies as ArrayList<MovieResponse>)
    }

    private fun showError(){
        context?.also {
            if(this.isVisible)
                showErrorDialog(it, { viewModel.loadMovies() }, { /* Do Nothing. */ })
        }
    }

    private fun showLoading(){
        homeLoading.isVisible(true)
    }

    private fun hideLoading(){
        homeLoading.isVisible(false)
    }

    override fun onItemClick(view: View, position: Int) {
        //TODO - Go to movie details activity.
        Toast.makeText(context, "Feature under development", Toast.LENGTH_LONG).show()
    }
}