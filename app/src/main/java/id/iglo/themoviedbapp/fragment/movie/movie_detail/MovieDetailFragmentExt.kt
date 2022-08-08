package id.iglo.themoviedbapp.fragment.movie.movie_detail

import android.view.View
import androidx.paging.LoadState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MovieDetailFragment.observeLiveData() {

    binding.recycler.adapter = adapter.withLoadStateFooter(loadStateAdapter)

    vm.getDetailAndReview(movieDetailArgs.movie)

    vm.loadVideo(movieDetailArgs.movie)

    vm.movieDetailData.observe(this) {
        binding.data = it.data
    }

    vm.movieReviewData.observe(this) {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }

    lifecycle.addObserver(binding.videoTrailer)

    vm.movieVideoData.observe(this) {
        binding.videoTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                if (it != null) {
                    youTubePlayer.loadVideo(it, 0F)
                }
            }
        })
    }

    adapter.addLoadStateListener {
        if(it.refresh is LoadState.Error && adapter.itemCount == 0){
            binding.layoutMovieDetail.visibility = View.GONE
            binding.retryButton2.visibility = View.VISIBLE
            binding.recycler.visibility = View.GONE
            binding.loadingButton.visibility = View.GONE
            binding.retryButton2.setOnClickListener{
                vm.getDetailAndReview(movieDetailArgs.movie)
                vm.loadVideo(movieDetailArgs.movie)
            }
        } else if (it.refresh is LoadState.Loading && adapter.itemCount == 0) {
            binding.retryButton2.visibility = View.GONE
            binding.layoutMovieDetail.visibility = View.GONE
            binding.recycler.visibility = View.GONE
            binding.loadingButton.visibility = View.VISIBLE
        } else if (it.refresh is LoadState.NotLoading) {
            binding.layoutMovieDetail.visibility = View.VISIBLE
            binding.retryButton2.visibility = View.GONE
            binding.loadingButton.visibility = View.GONE
            binding.recycler.visibility = View.VISIBLE
        }
    }
}