package com.rrzaniolo.movieapichallenge.presentation.main.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrzaniolo.movieapichallenge.R
import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.di.modules.loadFavoriteModule
import com.rrzaniolo.movieapichallenge.presentation.base.BaseRecyclerAdapter
import com.rrzaniolo.movieapichallenge.presentation.base.isVisible
import com.rrzaniolo.movieapichallenge.presentation.base.showErrorDialog
import com.rrzaniolo.movieapichallenge.presentation.detail.DetailView
import com.rrzaniolo.movieapichallenge.presentation.main.MovieListAdapter
import kotlinx.android.synthetic.main.view_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class FavoriteView : Fragment(), BaseRecyclerAdapter.OnItemClickListener{

    private val viewModel: FavoriteViewModel by viewModel()
    private val movieListAdapter by lazy { MovieListAdapter() }

    companion object {
        private lateinit var instance: FavoriteView

        fun newInstance(): FavoriteView {
            return if(::instance.isInitialized) instance else FavoriteView()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init(){
        loadFavoriteModule()
        initViewModel()
        setUpRecyclerView()
        viewModel.loadFavorites()
    }

    private fun initViewModel(){
        viewModel.favoriteViewState.observe(this, Observer{viewState ->
            hideLoading()
            when(viewState){
                is FavoriteViewState.ShowSuccess -> showSuccess(viewState.movies)
                is FavoriteViewState.ShowEmpty -> showEmpty()
                is FavoriteViewState.ShowError -> showError()
                is FavoriteViewState.ShowLoading -> showLoading()
            }
        })
    }

    private fun setUpRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL

        movieListAdapter.listener = this

        favoriteRecyclerView.layoutManager = layoutManager
        favoriteRecyclerView.itemAnimator = DefaultItemAnimator()
        favoriteRecyclerView.adapter = movieListAdapter
    }

    private fun showSuccess(movies: List<MovieResponse>) {
        favoriteRecyclerView.isVisible(true)
        movieListAdapter.setList(movies as ArrayList<MovieResponse>)
    }

    private fun showEmpty(){
        favoriteEmpty.isVisible(true)
        favoriteRecyclerView.isVisible(false)
        movieListAdapter.setList(ArrayList())
    }

    private fun showError(){
        context?.also {
            if(this.isVisible)
                showErrorDialog(it, { viewModel.loadFavorites() }, { /* Do Nothing. */ })
        }
    }

    private fun showLoading(){
        favoriteLoading.isVisible(true)
    }

    private fun hideLoading(){
        favoriteLoading.isVisible(false)
    }

    override fun onItemClick(view: View, position: Int) {
        Intent(activity, DetailView::class.java).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT).apply {
            this.putExtra(DetailView.MOVIE_DATA,  movieListAdapter.dataList[position])
            startActivity(this)
        }
    }
}