package ai.akun.moviees.ui.home.adapter

import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.ui.home.viewholder.TvShowViewHolder
import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class TvShowsAdapter(private val listener: (TvShow) -> Unit) :
    PagingDataAdapter<TvShow, RecyclerView.ViewHolder>(TvShowsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TvShowViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tvShow = getItem(position)
        tvShow?.let {
            (holder as TvShowViewHolder).apply {
                bindData(it)
                binding.root.setOnClickListener {
                    listener(tvShow)
                }
            }
        }
    }

    class TvShowsDiffCallBack : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }
    }
}