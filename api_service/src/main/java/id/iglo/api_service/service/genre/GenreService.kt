package id.iglo.api_service.service.genre

import id.iglo.api_service.Constants
import id.iglo.common.entities.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getAllGenre(
        @Query("api_key") apiKey : String = Constants.API_KEY_V3
    ) : Response<GenreResponse>
}