package id.iglo.themoviedbapp.fragment.movie.movie_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import id.iglo.common.ui.BaseFragment
import id.iglo.themoviedbapp.R
import id.iglo.themoviedbapp.databinding.LayoutMovieDetailFragmentBinding
import id.iglo.themoviedbapp.fragment.movie.movie_review.MovieReviewPagingAdapter
import id.iglo.themoviedbapp.fragment.movie.movie_review.MovieReviewPagingStateAdapter
import id.iglo.themoviedbapp.view_model.movie.MovieDetailViewModel

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, LayoutMovieDetailFragmentBinding>() {
    override val vm: MovieDetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_movie_detail_fragment

    val adapter = MovieReviewPagingAdapter()
    val loadStateAdapter = MovieReviewPagingStateAdapter()
    val movieDetailArgs : MovieDetailFragmentArgs by navArgs()

    override fun initBinding(binding: LayoutMovieDetailFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}