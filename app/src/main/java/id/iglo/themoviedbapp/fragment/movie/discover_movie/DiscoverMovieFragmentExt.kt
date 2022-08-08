package id.iglo.themoviedbapp.fragment.movie.discover_movie

import android.view.View
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverMovieFragment.observeLiveData() {
    binding.recycler.adapter = adapter.withLoadStateFooter(loadStateAdapter)
    vm.discoverMovie(discoverArgs.genre)
    vm.discoverData.observe(this) {
        binding.loadingContainer.visibility = View.GONE
        binding.recycler.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    adapter.addLoadStateListener {
        if (it.refresh is LoadState.Error && adapter.itemCount == 0) {
            binding.retryButton.visibility = View.VISIBLE
            binding.recycler.visibility = View.GONE
            binding.loadingContainer.visibility = View.GONE
            binding.retryButton.setOnClickListener {
                adapter.retry()
            }
        } else if (it.refresh is LoadState.Loading && adapter.itemCount == 0) {
            binding.recycler.visibility = View.GONE
            binding.retryButton.visibility = View.GONE
            binding.loadingContainer.visibility = View.VISIBLE
        } else if (it.refresh is LoadState.NotLoading) {
            binding.retryButton.visibility = View.GONE
            binding.recycler.visibility = View.VISIBLE
            binding.loadingContainer.visibility = View.GONE
        }
    }
}