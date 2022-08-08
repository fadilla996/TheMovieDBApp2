package id.iglo.themoviedbapp.view_model.movie

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.iglo.api_service.usecase.movie.GetMovieDetailUseCase
import id.iglo.api_service.usecase.movie.GetMovieReviewPagingUsecase
import id.iglo.api_service.usecase.movie.GetMovieVideoUsecase
import id.iglo.common.base_response.AppResponse
import id.iglo.common.entities.movie_detail.MovieDetailResponse
import id.iglo.common.entities.movie_review.Review
import id.iglo.common.ext.SingleLiveEvent
import id.iglo.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    application: Application,
    val movieDetailUseCase: GetMovieDetailUseCase,
    val movieReviewPagingUsecase: GetMovieReviewPagingUsecase,
    val movieVideoUsecase: GetMovieVideoUsecase
) : BaseViewModel(application) {

    val movieDetailData = MutableLiveData<AppResponse<MovieDetailResponse>>()
    val movieReviewData = MutableLiveData<PagingData<Review>>()
    val movieVideoData = SingleLiveEvent<String?>()

    fun getDetailAndReview(movie: Int) {
        viewModelScope.launch {
            movieDetailUseCase(movie).collect {
                movieDetailData.postValue(it)
            }
            movieReviewPagingUsecase.invoke(movie).collect {
                movieReviewData.postValue(it)
            }
        }
    }

    fun loadVideo(videoId: Int) {
        viewModelScope.launch {
            movieVideoUsecase.invoke(videoId).collect {
                it.data?.results?.get(0)?.key.let { it1 ->
                    movieVideoData.postValue(it1)
                }
            }
        }
    }
}