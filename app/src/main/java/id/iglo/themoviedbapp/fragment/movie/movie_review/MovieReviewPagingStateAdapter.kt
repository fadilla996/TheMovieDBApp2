package id.iglo.themoviedbapp.fragment.movie.movie_review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import id.iglo.themoviedbapp.databinding.DiscoverMovieItemStateLayoutBinding
import id.iglo.themoviedbapp.fragment.movie.discover_movie.DiscoverMovieFragment

class MovieReviewPagingStateAdapter : LoadStateAdapter<MovieReviewStateViewHolder>(){
    override fun onBindViewHolder(holder: MovieReviewStateViewHolder, loadState: LoadState) {
        return holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieReviewStateViewHolder {
        return MovieReviewStateViewHolder(
            DiscoverMovieItemStateLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply { this.bind(loadState) }
    }
}

class MovieReviewStateViewHolder (private val binding : DiscoverMovieItemStateLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        when(loadState) {
            is LoadState.Error -> {
                binding.errorContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.VISIBLE
            }
            is LoadState.Loading -> {
                binding.loadingContainer.visibility = View.VISIBLE
                binding.errorContainer.visibility = View.GONE
            }
            is LoadState.NotLoading -> {
                binding.loadingContainer.visibility = View.GONE
                binding.errorContainer.visibility = View.GONE
            }
        }
    }
}