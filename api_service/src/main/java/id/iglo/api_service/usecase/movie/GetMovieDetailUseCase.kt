package id.iglo.api_service.usecase.movie

import id.iglo.api_service.service.movie.GetMovieDetailService
import id.iglo.common.base_response.AppResponse
import id.iglo.common.entities.movie_detail.MovieDetailResponse
import kotlinx.coroutines.flow.flow

class GetMovieDetailUseCase(private val getMovieDetailService: GetMovieDetailService) {
    operator fun invoke(movieId: Int) = flow {
        try {
            emit(AppResponse.loading())
            val data = getMovieDetailService.getMovieDetail(movieId)
            if (data.isSuccessful) {
                data.body()?.let {
                    emit(AppResponse.success(it))
                } ?: run {
                    emit(
                        AppResponse.errorBackend<MovieDetailResponse>(
                            data.code(),
                            data.errorBody()
                        )
                    )
                }
            } else {
                emit(AppResponse.errorBackend(data.code(), data.errorBody()))
            }
        } catch (e: Exception) {
            emit(AppResponse.errorSystem(e))
        }
    }
}