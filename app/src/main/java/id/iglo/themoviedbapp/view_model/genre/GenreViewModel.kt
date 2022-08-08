package id.iglo.themoviedbapp.view_model.genre

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.SelectionTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import id.iglo.api_service.usecase.genre.GenreUseCase
import id.iglo.common.base_response.AppResponse
import id.iglo.common.entities.genre.Genre
import id.iglo.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(application: Application, val genreUseCase: GenreUseCase) :
    BaseViewModel(application) {
    var selectionTracker: SelectionTracker<Long>? = null
    val genreData = MutableLiveData<AppResponse<List<Genre>>>()

    init {
        getAllGenres()
    }

    fun getAllGenres() {
        viewModelScope.launch {
            genreUseCase().collect {
                genreData.postValue(it)
            }
        }
    }
}