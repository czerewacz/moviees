package ai.akun.moviees.ui.home.adapter

import ai.akun.core.extensions.inflate
import ai.akun.moviees.R
import ai.akun.moviees.commons.loadUrl
import ai.akun.moviees.databinding.ViewTvShowBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.ui.home.diffutil.TvShowDiffUtil
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class TvShowsAdapter(private val listener: (TvShow) -> Unit) :
    RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    private val oldItems = ArrayList<TvShow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_tv_show, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = oldItems[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener { listener(tvShow) }
    }

    override fun getItemCount(): Int = oldItems.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewTvShowBinding.bind(view)
        fun bind(tvShow: TvShow) = with(binding) {
            tvShowTitle.text = tvShow.name
            tvShowCover.loadUrl("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
            voteAverage.text = tvShow.voteAverage.toString()
            voteCount.text = tvShow.voteCount.toString()
        }
    }

    fun setData(newList: List<TvShow>, rv: RecyclerView) {
        val tvShowsDiff = TvShowDiffUtil(oldItems, newList)
        val diff = DiffUtil.calculateDiff(tvShowsDiff)
        oldItems.addAll(newList)
        diff.dispatchUpdatesTo(this)
        rv.scrollToPosition(oldItems.size - newList.size)
    }
}