package ai.akun.moviees.ui.detail

import ai.akun.moviees.commons.loadUrl
import ai.akun.moviees.databinding.FragmentDetailBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.feature.tvshows.presentation.DetailViewModel
import ai.akun.moviees.ui.home.TvShowsAdapter
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: TvShowsAdapter
    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModel()
    //TODO pass args to ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.getSimilarTvShows()

        startObserving()
        updateLayoutDetail(args.tvShow)

        return binding.root

    }


    private fun startObserving() {
        viewModel.tvShow.observe(viewLifecycleOwner, { tvShow ->
            updateLayoutDetail(tvShow)
        })

        viewModel.data.observe(viewLifecycleOwner, { tvShow ->
            updateLayoutDetail(tvShow)
        })
    }

    private fun updateLayoutDetail(tvShow: TvShow) = with(binding) {
        collapsingLayout.apply {
            setExpandedTitleColor(Color.WHITE)
            setCollapsedTitleTextColor(Color.WHITE)
        }
        tvShowDetailToolbar.title = tvShow.name
        tvShowDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${tvShow.backdropPath}")
        tvShowDetailSummary.text = tvShow.overview
        tvShowDetailInfo.setTvShow(tvShow)
    }


}