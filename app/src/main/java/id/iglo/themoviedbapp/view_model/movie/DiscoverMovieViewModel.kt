package id.iglo.themoviedbapp.view_model.movie

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.iglo.api_service.usecase.movie.DiscoverMoviePagingUsecase
import id.iglo.common.entities.discover_movie.Movie
import id.iglo.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverMovieViewModel @Inject constructor(
    application: Application,
    val discoverMoviePagingUsecase: DiscoverMoviePagingUsecase
) : BaseViewModel(application) {
    val discoverData = MutableLiveData<PagingData<Movie>>()

    fun discoverMovie(genre: String) {
        viewModelScope.launch {
            discoverMoviePagingUsecase.invoke(genre).flow.collect {
                discoverData.postValue(it)
            }
        }
    }
}