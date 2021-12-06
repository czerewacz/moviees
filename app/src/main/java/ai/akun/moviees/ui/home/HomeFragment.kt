package ai.akun.moviees.ui.home

import ai.akun.core.extensions.gone
import ai.akun.core.extensions.visible
import ai.akun.moviees.databinding.FragmentHomeBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.feature.tvshows.presentation.TopRatedViewModel
import ai.akun.moviees.ui.home.adapter.TvShowsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
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

        initUi()
        startObserving()
        viewModel.getTopRatedTvShows()

        return binding.root
    }

    private fun initUi() {
        adapter = TvShowsAdapter(::navigateToTvShow)
        binding.recycler.adapter = adapter

        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getTopRatedTvShows()
                }
            }
        })
    }

    private fun startObserving() {
        viewModel.uiState.observe(viewLifecycleOwner, { uiState ->
            updateUi(uiState)
        })

        viewModel.topRated.observe(viewLifecycleOwner, { topRated ->
            adapter.setData(topRated.results, binding.recycler)
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->
            if (error != "") {
                binding.errorText.text = error
            }
        })
    }

    private fun updateUi(uiState: TopRatedViewModel.UIState) {
        when (viewModel.page.value) {
            1 -> {
                if (uiState == TopRatedViewModel.UIState.FirstLoading) {
                    binding.progress.visible()
                    binding.recycler.suppressLayout(true)
                } else binding.progress.gone()
            }
            else -> {
                if (uiState == TopRatedViewModel.UIState.MoreLoading) {
                    binding.bottomProgressBar.visible()
                    binding.recycler.suppressLayout(true)
                } else binding.bottomProgressBar.gone()
            }
        }

        if (uiState == TopRatedViewModel.UIState.Success) {
            binding.recycler.visible()
            binding.recycler.suppressLayout(false)
        }

        if (uiState == TopRatedViewModel.UIState.Error) {
            binding.errorText.visible()
            binding.recycler.gone()
        } else binding.errorText.gone()
    }

    private fun navigateToTvShow(tvShow: TvShow) {
        clearData()
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(tvShow)
        findNavController().navigate(action)
    }

    private fun clearData(){
        viewModel.clearData()
    }
}