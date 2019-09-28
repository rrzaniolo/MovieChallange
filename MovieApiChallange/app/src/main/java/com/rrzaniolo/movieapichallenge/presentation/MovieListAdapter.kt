package com.rrzaniolo.movieapichallenge.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rrzaniolo.movieapichallenge.BuildConfig
import com.rrzaniolo.movieapichallenge.R
import com.rrzaniolo.movieapichallenge.data.models.GenreResponse
import com.rrzaniolo.movieapichallenge.data.models.MovieResponse
import com.rrzaniolo.movieapichallenge.presentation.base.BaseRecyclerAdapter
import com.rrzaniolo.movieapichallenge.presentation.base.toDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_movie.view.*

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class MovieListAdapter: BaseRecyclerAdapter<MovieResponse>() {

    var genreList: GenreResponse? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_movie, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(genreList, dataList[position])
    }

    class ViewHolder(
        private val view: View,
        private val onClickListener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(view) {

        fun bind(genres: GenreResponse?, movie: MovieResponse) = with(view) {
            Picasso.get().load(BuildConfig.IMAGE_URL + movie.posterPath).into(movieImage)
            movieTitle.text = movie.title
            genres?.let {

                var genresNames = ""
                movie.genreIds?.forEach {id -> genresNames += genres.genreList.single { g -> g.id == id }.name + ", " }

                movieSubtitle.text =
                    resources.getString(
                        R.string.movie_subtitle,
                        genresNames.substring(0, genresNames.length-2), /* Remove last space and comma. */
                        movie.releaseDate?.toDate()

                    )
            } ?: run {
                movieSubtitle.text =
                    resources.getString(
                        R.string.movie_subtitle,
                        movie.releaseDate?.toDate(),
                        ""
                    )
            }

            movieView.setOnClickListener {
                onClickListener?.onItemClick(view, adapterPosition)
            }
        }
    }
}