package ai.akun.moviees.ui.similar

import ai.akun.core.extensions.basicDiffUtil
import ai.akun.core.extensions.inflate
import ai.akun.moviees.R
import ai.akun.moviees.commons.loadUrl
import ai.akun.moviees.databinding.ViewSwipeBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TvSwipeAdapter() :
    RecyclerView.Adapter<TvSwipeAdapter.ViewHolder>() {

    var tvShows: List<TvShow> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_swipe, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = tvShows.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewSwipeBinding.bind(view)
        fun bind(tvShow: TvShow) = with(binding) {
            collapsingLayout.apply {
                setExpandedTitleColor(Color.WHITE)
                setCollapsedTitleTextColor(Color.WHITE)
            }
            tvShowDetailToolbar.title = tvShow.name
            tvShowDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${tvShow.backdropPath}")
            tvShowDetailSummary.text = tvShow.overview
            tvShowDetailInfo.setTvShow(tvShow)
            //tvShowCover.loadUrl("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
        }
    }
}