package id.iglo.themoviedbapp.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.iglo.api_service.RetrofitClient
import id.iglo.api_service.service.genre.GenreService
import id.iglo.api_service.service.movie.DiscoverMovieService
import id.iglo.api_service.service.movie.GetMovieDetailService
import id.iglo.api_service.service.movie.GetMovieReviewService
import id.iglo.api_service.service.movie.GetMovieVideoService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context : Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenreService (retrofit: Retrofit) : GenreService = retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideDiscoverMovieService (retrofit: Retrofit) : DiscoverMovieService = retrofit.create(DiscoverMovieService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailService(retrofit: Retrofit) : GetMovieDetailService = retrofit.create(GetMovieDetailService::class.java)

    @Provides
    @Singleton
    fun provideMovieReviewService(retrofit: Retrofit) : GetMovieReviewService = retrofit.create(GetMovieReviewService::class.java)

    @Provides
    @Singleton
    fun provideMovieVideoService (retrofit: Retrofit) : GetMovieVideoService = retrofit.create(GetMovieVideoService::class.java)
}