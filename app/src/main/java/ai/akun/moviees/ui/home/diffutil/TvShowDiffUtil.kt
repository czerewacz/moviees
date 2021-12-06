package ai.akun.moviees.ui.home.diffutil

import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import androidx.recyclerview.widget.DiffUtil

class TvShowDiffUtil(
    private val oldList: List<TvShow>,
    private val newList: List<TvShow>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTvShow = oldList[oldItemPosition]
        val newTvShow = newList[newItemPosition]
        return oldTvShow.id == newTvShow.id
    }
}