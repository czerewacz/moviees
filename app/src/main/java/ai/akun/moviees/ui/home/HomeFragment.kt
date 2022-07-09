package ai.akun.moviees.ui.home

import ai.akun.core.extensions.gone
import ai.akun.core.extensions.visible
import ai.akun.moviees.databinding.FragmentHomeBinding
import ai.akun.moviees.feature.tvshows.domain.model.TvShow
import ai.akun.moviees.feature.tvshows.presentation.DetailViewModel
import ai.akun.moviees.feature.tvshows.presentation.TopRatedViewModel
import ai.akun.moviees.ui.home.adapter.TvShowsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TvShowsAdapter
    private val viewModel: TopRatedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getTopRatedTvShows()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initUi()
        startObserving()


        return binding.root
    }

    private fun initUi() {
        adapter = TvShowsAdapter(::navigateToTvShow)
        binding.recycler.adapter = adapter
    }

    private fun startObserving() {

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            updateUi(uiState)
        }

        viewModel.topRated.observe(viewLifecycleOwner) { topRated ->
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(topRated)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != "") {
                binding.errorText.text = error
            }
        }
    }

    private fun updateUi(uiState: TopRatedViewModel.UIState) {

        if (uiState == TopRatedViewModel.UIState.Loading) {
            binding.progress.visible()
            binding.recycler.suppressLayout(true)
        } else binding.progress.gone()

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
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(tvShow)
        findNavController().navigate(action)
    }

}