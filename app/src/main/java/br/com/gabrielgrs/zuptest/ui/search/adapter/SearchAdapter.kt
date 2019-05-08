package br.com.gabrielgrs.zuptest.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.model.moviesearch.MovieSearchDto
import com.bumptech.glide.Glide

class SearchAdapter(
    private var context: Context,
    private var moviesList: MutableList<MovieSearchDto>,
    private var onMovieClickListener: (MovieSearchDto) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: MovieSearchDto = moviesList[position]
        holder.bind(movie, context, onMovieClickListener)
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie_search, parent, false)) {

        private var mTitleTextView: TextView? = null
        private var mReleaseYearTextView: TextView? = null
        private var mBannerImageView: ImageView? = null


        init {
            mTitleTextView = itemView.findViewById(R.id.item_movie_search_title_textview)
            mReleaseYearTextView = itemView.findViewById(R.id.item_movie_search_release_year_textview)
            mBannerImageView = itemView.findViewById(R.id.item_movie_search_banner_imageview)
        }

        fun bind(movie: MovieSearchDto, context: Context, clickListener: (MovieSearchDto) -> Unit) {
            itemView.setOnClickListener { clickListener(movie) }

            loadImage(context, movie.poster)

            mTitleTextView!!.text = movie.title
            mReleaseYearTextView!!.text = movie.year
        }

        private fun loadImage(context: Context, moviePoster: String?) {
            Glide.with(context)
                .load(moviePoster)
                .into(mBannerImageView)
        }

    }

}