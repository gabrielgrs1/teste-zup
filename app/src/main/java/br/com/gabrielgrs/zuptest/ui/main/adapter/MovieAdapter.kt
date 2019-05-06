package br.com.gabrielgrs.zuptest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielgrs.zuptest.R
import br.com.gabrielgrs.zuptest.model.movie.MovieDto

class MovieAdapter(
    private var moviesList: MutableList<MovieDto>,
    private var onMovieClickListener: (MovieDto) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: MovieDto = moviesList[position]
        holder.bind(movie, onMovieClickListener)
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie_main, parent, false)) {

        private var mTitleTextView: TextView? = null
        private var mReleaseYearTextView: TextView? = null


        init {
            mTitleTextView = itemView.findViewById(R.id.item_movie_movie_name_textview)
            mReleaseYearTextView = itemView.findViewById(R.id.item_movie_release_year_textview)
        }

        fun bind(movie: MovieDto, clickListener: (MovieDto) -> Unit) {
            itemView.setOnClickListener { clickListener(movie) }

            mTitleTextView!!.text = movie.title
            mReleaseYearTextView!!.text = movie.year
        }

    }

}
