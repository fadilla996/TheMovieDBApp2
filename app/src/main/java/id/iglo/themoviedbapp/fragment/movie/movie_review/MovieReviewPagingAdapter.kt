package id.iglo.themoviedbapp.fragment.movie.movie_review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.iglo.common.entities.movie_review.Review
import id.iglo.themoviedbapp.databinding.MovieReviewItemLayoutBinding

class MovieReviewPagingAdapter : PagingDataAdapter<Review, MovieReviewViewHolder>(differ) {
    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
            MovieReviewItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }

        }
    }

}

class MovieReviewViewHolder(private val binding: MovieReviewItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(review: Review?) {
        binding.data = review
    }
}