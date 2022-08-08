package id.iglo.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.iglo.api_service.service.movie.DiscoverMovieService
import id.iglo.common.entities.discover_movie.Movie

class DiscoverMovieDataSource(
    private val discoverMovieService: DiscoverMovieService,
    private val genres: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else -1

        try {
            val data = discoverMovieService.discoverMovieByGenre(genres, page)
            if (data.isSuccessful) {
                data.body()?.let {
                    val nextPage = if (it.results.isEmpty()) null else page + 1
                    return LoadResult.Page(data = it.results, prevPage, nextPage)
                } ?: run {
                    return LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else {
                return LoadResult.Error(Exception("Error Backend : ${data.code()}"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object DiscoverMoviePager {
        fun createPager(
            pageSize: Int,
            discoverMovieService: DiscoverMovieService,
            genres: String
        ): Pager<Int, Movie> = Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                DiscoverMovieDataSource(discoverMovieService, genres)
            }
        )
    }
}