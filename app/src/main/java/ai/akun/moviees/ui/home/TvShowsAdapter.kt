package ai.akun.moviees.ui.home

import ai.akun.core.extensions.basicDiffUtil
import ai.akun.core.extensions.inflate
import ai.akun.moviees.R
import ai.akun.moviees.commons.loadUrl
import ai.akun.moviees.databinding.ViewTvShowBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TvShowsAdapter(private val listener: (TvShow) -> Unit) :
    RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    var tvShows: List<TvShow> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_tv_show, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener { listener(tvShow) }
    }

    override fun getItemCount(): Int = tvShows.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewTvShowBinding.bind(view)
        fun bind(tvShow: TvShow) = with(binding) {
            tvShowTitle.text = tvShow.name
            tvShowCover.loadUrl("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
            voteAverage.text = tvShow.voteAverage.toString()
            voteCount.text = tvShow.voteCount.toString()
        }
    }
}