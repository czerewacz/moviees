package ai.akun.moviees.ui.similar

import ai.akun.moviees.databinding.FragmentSwipeBinding
import ai.akun.moviees.feature.tvshows.presentation.DetailViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SwipeFragment : Fragment() {

    private lateinit var binding: FragmentSwipeBinding
    private lateinit var adapter: TvSwipeAdapter
    private val args: SwipeFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSwipeBinding.inflate(inflater, container, false)
        adapter = TvSwipeAdapter()
        binding.recycler.adapter = adapter
        updateUI()
        return binding.root
    }

    private fun updateUI() {
        adapter.tvShows = viewModel.topRated.value?.results!!
        binding.recycler.layoutManager?.scrollToPosition(args.position)

        binding.closeSwipe.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}