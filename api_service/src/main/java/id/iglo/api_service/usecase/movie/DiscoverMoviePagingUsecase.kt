package id.iglo.api_service.usecase.movie

import id.iglo.api_service.Constants
import id.iglo.api_service.paging.DiscoverMovieDataSource
import id.iglo.api_service.service.movie.DiscoverMovieService

class DiscoverMoviePagingUsecase(
    private val discoverMovieService: DiscoverMovieService
) {
    operator fun invoke(genre: String) =
        DiscoverMovieDataSource.createPager(
            Constants.DEFAULT_PAGE_SIZE,
            discoverMovieService,
            genre
        )
}