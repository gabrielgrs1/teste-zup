package br.com.gabrielgrs.zuptest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.application.ZupTestApplication
import br.com.gabrielgrs.zuptest.model.movie.MovieDto
import com.bumptech.glide.Glide

class MainAdapter(
    private var context: Context,
    private var moviesList: MutableList<MovieDto>,
    private var onMovieClickListener: (MovieDto) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: MovieDto = moviesList[position]
        holder.bind(context, movie, onMovieClickListener)
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie_main, parent, false)) {

        private var mTitleTextView: TextView? = null
        private var mReleaseYearTextView: TextView? = null
        private var mBannerImageView: ImageView? = null
        private var mGenreTextView: TextView? = null


        init {
            mTitleTextView = itemView.findViewById(R.id.item_movie_main_title_textview)
            mReleaseYearTextView = itemView.findViewById(R.id.item_movie_main_release_year_textview)
            mBannerImageView = itemView.findViewById(R.id.item_movie_main_banner_imageview)
            mGenreTextView = itemView.findViewById(R.id.item_movie_main_genre_textview)
        }

        fun bind(context: Context, movie: MovieDto, clickListener: (MovieDto) -> Unit) {
            itemView.setOnClickListener { clickListener(movie) }

            loadImage(context, movie.poster)
            setTexts(movie)
        }

        private fun setTexts(movie: MovieDto) {
            mTitleTextView!!.text = movie.title
            mGenreTextView!!.text = movie.genre

            val yearRelease =
                ZupTestApplication.applicationContext().getString(R.string.item_movie_search_release_date_textview, movie.year)
            mReleaseYearTextView!!.text = yearRelease
        }

        private fun loadImage(context: Context, moviePoster: String?) {
            if (moviePoster == "N/A") {
                mBannerImageView!!.setImageDrawable(ZupTestApplication.applicationContext().getDrawable(R.drawable.error_image))
            } else {
                Glide.with(context)
                    .load(moviePoster)
                    .thumbnail(.1f)
                    .into(mBannerImageView)
            }

        }

    }

}
