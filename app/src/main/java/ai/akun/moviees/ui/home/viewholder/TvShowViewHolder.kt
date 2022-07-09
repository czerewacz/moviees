package ai.akun.moviees.ui.home.viewholder

import ai.akun.moviees.commons.loadUrl
import ai.akun.moviees.databinding.ViewTvShowBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TvShowViewHolder(val binding: ViewTvShowBinding ) :
RecyclerView.ViewHolder(binding.root){

    fun bindData(tvShow: TvShow){
        with(binding){
            tvShowTitle.text = tvShow.name
            tvShowCover.loadUrl("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
            voteAverage.text = tvShow.voteAverage.toString()
            voteCount.text = tvShow.voteCount.toString()
        }
    }

    companion object {
        fun create(parent: ViewGroup): TvShowViewHolder {
            val binding = ViewTvShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return TvShowViewHolder(binding)
        }
    }
}
