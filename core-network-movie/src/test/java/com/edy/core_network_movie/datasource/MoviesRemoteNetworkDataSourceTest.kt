package com.edy.core_network_movie.datasource

import com.edy.network.service.MovieServices
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MoviesRemoteNetworkDataSourceTest : BaseRemoteNetworkDataSourceTest() {
    private lateinit var movieServices: MovieServices

    @Before
    override fun setUp() {
        super.setUp()
        movieServices = getService()
    }

    @Test
    fun addition_isCorrect() {
        runBlocking {
            val movies = movieServices.getFilms()
            assertEquals(true, movies.data?.isNotEmpty())
        }
    }
}