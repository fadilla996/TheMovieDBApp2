package id.iglo.api_service.usecase.movie

import id.iglo.api_service.Constants
import id.iglo.api_service.paging.GetMovieReviewDataSource
import id.iglo.api_service.service.movie.GetMovieReviewService

class GetMovieReviewPagingUsecase(private val getMovieReviewService: GetMovieReviewService) {
    operator fun invoke(
        movieId : Int
    ) = GetMovieReviewDataSource.createPager(
        Constants.DEFAULT_PAGE_SIZE, getMovieReviewService, movieId
    ).flow
}