package ai.akun.moviees.ui.home

import ai.akun.core.extensions.gone
import ai.akun.core.extensions.visible
import ai.akun.moviees.databinding.FragmentHomeBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.feature.tvshows.presentation.TopRatedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TvShowsAdapter
    private val viewModel: TopRatedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        adapter = TvShowsAdapter(::navigateToTvShow)
        binding.recycler.adapter = adapter
        viewModel.getTopRatedTvShows()

        startObserving()

        return binding.root
    }

    private fun startObserving() {
        viewModel.uiState.observe(viewLifecycleOwner, { uiState ->
            updateUi(uiState)
        })

        viewModel.topRated.observe(viewLifecycleOwner, { topRated ->
            adapter.tvShows = topRated.results
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->
            if (error != "") {
                binding.errorText.text = error
            }
        })
    }

    private fun updateUi(uiState: TopRatedViewModel.UIState) {
        if (uiState == TopRatedViewModel.UIState.FirstLoading) binding.progress.visible()
        else binding.progress.gone()

        if (uiState == TopRatedViewModel.UIState.Success) binding.recycler.visible()
        else binding.recycler.gone()

        if (uiState == TopRatedViewModel.UIState.Error) binding.errorText.visible()
        else binding.errorText.gone()
    }

    private fun navigateToTvShow(tvShow: TvShow) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(tvShow)
        findNavController().navigate(action)
    }
}