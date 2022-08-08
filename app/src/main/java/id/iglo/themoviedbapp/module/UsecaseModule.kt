package id.iglo.themoviedbapp.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.iglo.api_service.service.genre.GenreService
import id.iglo.api_service.service.movie.DiscoverMovieService
import id.iglo.api_service.service.movie.GetMovieDetailService
import id.iglo.api_service.service.movie.GetMovieReviewService
import id.iglo.api_service.service.movie.GetMovieVideoService
import id.iglo.api_service.usecase.genre.GenreUseCase
import id.iglo.api_service.usecase.movie.DiscoverMoviePagingUsecase
import id.iglo.api_service.usecase.movie.GetMovieDetailUseCase
import id.iglo.api_service.usecase.movie.GetMovieReviewPagingUsecase
import id.iglo.api_service.usecase.movie.GetMovieVideoUsecase

@Module
@InstallIn(ViewModelComponent::class)
class UsecaseModule {

    @Provides
    fun provideGenreUsecase(genreService: GenreService) = GenreUseCase(genreService)

    @Provides
    fun provideDiscoverMoviePagingUsecase(discoverMovieService: DiscoverMovieService) = DiscoverMoviePagingUsecase(discoverMovieService)

    @Provides
    fun provideMovieDetailUsecase(movieDetailService: GetMovieDetailService) = GetMovieDetailUseCase(movieDetailService)

    @Provides
    fun provideMovieReviewPagingUsecase(movieReviewService: GetMovieReviewService) = GetMovieReviewPagingUsecase(movieReviewService)

    @Provides
    fun provideMovieVideoUsecase(movieVideoService: GetMovieVideoService) = GetMovieVideoUsecase(movieVideoService)
}