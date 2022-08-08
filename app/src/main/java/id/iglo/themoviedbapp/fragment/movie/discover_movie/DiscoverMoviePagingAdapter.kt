package id.iglo.themoviedbapp.fragment.movie.discover_movie


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.iglo.common.entities.discover_movie.Movie
import id.iglo.themoviedbapp.databinding.LayoutDiscoverMovieItemBinding

class DiscoverMoviePagingAdapter(
    val selectMovie: (Movie) -> Unit
) : PagingDataAdapter<Movie, DiscoverMovieViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: DiscoverMovieViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
        holder.binding.cardView.setOnClickListener {
            data?.let { it1 ->
                selectMovie(it1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverMovieViewHolder {
        return DiscoverMovieViewHolder(
            LayoutDiscoverMovieItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }
}

class DiscoverMovieViewHolder(val binding: LayoutDiscoverMovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

}