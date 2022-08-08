package id.iglo.api_service.usecase.genre

import id.iglo.api_service.service.genre.GenreService
import id.iglo.common.base_response.AppResponse
import id.iglo.common.entities.genre.Genre
import kotlinx.coroutines.flow.flow

class GenreUseCase(val genreService: GenreService) {
    operator fun invoke() = flow<AppResponse<List<Genre>>> {
        try {
            emit(AppResponse.loading())
            val result = genreService.getAllGenre()
            if (result.isSuccessful) {
                result.body()?.let {
                    emit(AppResponse.success(it.genres))
                } ?: run {
                    emit(AppResponse.errorBackend(result.code(), result.errorBody()))
                }
            } else {
                emit(AppResponse.errorBackend(result.code(), result.errorBody()))
            }
        } catch (e: Exception) {
            emit(AppResponse.errorSystem(e))
        }
    }
}