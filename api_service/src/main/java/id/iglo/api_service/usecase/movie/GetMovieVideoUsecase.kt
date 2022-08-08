package id.iglo.api_service.usecase.movie

import id.iglo.api_service.service.movie.GetMovieVideoService
import id.iglo.common.base_response.AppResponse
import id.iglo.common.entities.movie_video.MovieVideoResponse
import kotlinx.coroutines.flow.flow

class GetMovieVideoUsecase(private val getMovieVideoService: GetMovieVideoService) {
    operator fun invoke(movieId: Int) = flow {
        try {
            emit(AppResponse.loading())
            val data = getMovieVideoService.getMovieVideo(movieId)
            if (data.isSuccessful) {
                data.body()?.let {
                    emit(AppResponse.success(it))
                } ?: run {
                    emit(AppResponse.errorBackend<MovieVideoResponse>(data.code(), data.errorBody()))
                }
            } else {
                emit(AppResponse.errorBackend(data.code(), data.errorBody()))
            }
        } catch (e : Exception) {
            emit(AppResponse.errorSystem(e))
        }
    }
}