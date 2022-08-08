package id.iglo.api_service.service.movie

import id.iglo.api_service.Constants
import id.iglo.common.entities.movie_detail.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String = Constants.API_KEY_V3
    ) : Response<MovieDetailResponse>
}