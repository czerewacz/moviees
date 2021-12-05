package ai.akun.moviees.ui.detail

import ai.akun.core.extensions.basicDiffUtil
import ai.akun.core.extensions.inflate
import ai.akun.moviees.R
import ai.akun.moviees.commons.loadUrl
import ai.akun.moviees.databinding.ViewSimilarBinding
import ai.akun.moviees.databinding.ViewTvShowBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TvSimilarAdapter(private val listener: (TvShow) -> Unit) :
    RecyclerView.Adapter<TvSimilarAdapter.ViewHolder>() {



    var tvShows: List<TvShow> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_similar, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener { listener(tvShow) }
    }

    override fun getItemCount(): Int = tvShows.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewSimilarBinding.bind(view)
        fun bind(tvShow: TvShow) = with(binding) {
            tvShowCover.loadUrl("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
        }
    }
}