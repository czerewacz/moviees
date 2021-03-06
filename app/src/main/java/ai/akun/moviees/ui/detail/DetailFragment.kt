package ai.akun.moviees.ui.detail

import ai.akun.core.extensions.gone
import ai.akun.core.extensions.visible
import ai.akun.moviees.commons.loadUrl
import ai.akun.moviees.databinding.FragmentDetailBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.feature.tvshows.presentation.DetailViewModel
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: TvSimilarAdapter
    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.setTvShow(args.tvShow)
        viewModel.getSimilarTvShows(args.tvShow.genreIds.first())

        adapter = TvSimilarAdapter(::navigateToSwipe)
        binding.recycler.adapter = adapter
        binding.icNavBack.setOnClickListener {
            findNavController().popBackStack()
        }
        startObserving()

        updateDetailLayout(args.tvShow)
        return binding.root
    }

    private fun startObserving() {

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            updateSimilarLayout(uiState)
        }

        viewModel.similarTvShows.observe(viewLifecycleOwner) { similarTvShows ->
            adapter.tvShows = similarTvShows.results.toMutableList()
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != "") {
                binding.errorText.text = error
            }
        }
    }

    private fun updateDetailLayout(tvShow: TvShow) = with(binding) {
        collapsingLayout.apply {
            setExpandedTitleColor(Color.WHITE)
            setCollapsedTitleTextColor(Color.WHITE)
        }
        tvShowDetailToolbar.title = tvShow.name
        tvShowDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${tvShow.backdropPath}")
        tvShowDetailSummary.text = tvShow.overview
        tvShowDetailInfo.setTvShow(tvShow)
    }

    private fun updateSimilarLayout(uiState: DetailViewModel.UIState) {
        if (uiState == DetailViewModel.UIState.Loading) binding.progress.visible()
        else binding.progress.gone()

        if (uiState == DetailViewModel.UIState.Success) binding.recycler.visible()
        else binding.recycler.gone()

        if (uiState == DetailViewModel.UIState.Error) binding.errorText.visible()
        else binding.errorText.gone()
    }

    private fun navigateToSwipe(position: Int) {
        val action = DetailFragmentDirections.actionDetailFragmentToSwipeFragment(position)
        findNavController().navigate(action)
    }

}