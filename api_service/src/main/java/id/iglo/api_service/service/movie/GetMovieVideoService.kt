package id.iglo.api_service.service.movie

import id.iglo.api_service.Constants
import id.iglo.common.entities.movie_video.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieVideoService {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String = Constants.API_KEY_V3
    ) : Response<MovieVideoResponse>
}