package id.iglo.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.iglo.api_service.service.movie.GetMovieReviewService
import id.iglo.common.entities.movie_review.Review

class GetMovieReviewDataSource(
    private val getMovieReviewService: GetMovieReviewService,
    private val movieId: Int
) : PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else -1

        try {
            val data = getMovieReviewService.getMovieReview(movieId, page = page)
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

    companion object GetMovieReviewPager {
        fun createPager(
            pageSize: Int,
            movieReviewService: GetMovieReviewService,
            movieId: Int
        ): Pager<Int, Review> = Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                GetMovieReviewDataSource(movieReviewService, movieId)
            }
        )
    }
}