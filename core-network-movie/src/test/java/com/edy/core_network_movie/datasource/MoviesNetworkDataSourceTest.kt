package com.edy.core_network_movie.datasource

import com.edy.core_domain.usecase.flow.Resource
import com.edy.core_error.model.ErrorEntity
import com.edy.core_network.utils.nonStaticFieldsCount
import com.edy.network.service.MovieServices
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesNetworkDataSourceTest : BaseNetworkDataSourceTest() {
    private lateinit var movieNetworkDataSource: MovieServices

    @Before
    override fun setUp() {
        super.setUp()
        movieNetworkDataSource = getService()
    }

    @Test
    fun testGetRoomsRequestOK() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(getMoviesResponse))

        movieNetworkDataSource.getFilms()

        val request = mockWebServer.takeRequest(1, TimeUnit.SECONDS)!!
        assertEquals("/films", request.requestUrl!!.encodedPath)
        assertEquals("GET", request.method)
        assertEquals("application/json", request.getHeader("Content-Type"))
        assertEquals("application/json", request.getHeader("Accept"))
    }

    @Test
    fun testGetMoviesResponseOK() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(getMoviesResponse))

        val response = movieNetworkDataSource.getFilms().data!!
        assertNotNull(response)
        assertEquals(3, response.size)
        with(response[0]) {
            assertEquals(17, this.nonStaticFieldsCount)
            assertEquals("2baf70d1-42bb-4437-b551-e5fed5a87abe", id)
            assertEquals("Castle in the Sky", title)
            assertEquals("天空の城ラピュタ", originalTitle)
            assertEquals("Tenkū no shiro Rapyuta", originalTitleRomanised)
            assertEquals("https://image.tmdb.org/t/p/w600_and_h900_bestv2/npOnzAbLh6VOIu3naU5QaEcTepo.jpg", image)
            assertEquals("https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg", movieBanner)
            assertEquals(
                "The orphan Sheeta inherited a mysterious crystal that links her to the mythical sky-kingdom of Laputa. With the help of resourceful Pazu and a rollicking band of sky pirates, she makes her way to the ruins of the once-great civilization. Sheeta and Pazu must outwit the evil Muska, who plans to use Laputa's science to make himself ruler of the world.",
                description)
            assertEquals("Hayao Miyazaki", director)
            assertEquals("Isao Takahata", producer)
            assertEquals("1986", releaseDate)
            assertEquals("124", runningTime)
            assertEquals("95", rtScore)
            assertEquals(7, people.size)
            assertEquals("https://ghibliapi.herokuapp.com/people/598f7048-74ff-41e0-92ef-87dc1ad980a9", people[0])
            assertEquals("https://ghibliapi.herokuapp.com/people/fe93adf2-2f3a-4ec4-9f68-5422f1b87c01", people[1])
            assertEquals("https://ghibliapi.herokuapp.com/people/3bc0b41e-3569-4d20-ae73-2da329bf0786", people[2])
            assertEquals("https://ghibliapi.herokuapp.com/people/40c005ce-3725-4f15-8409-3e1b1b14b583", people[3])
            assertEquals("https://ghibliapi.herokuapp.com/people/5c83c12a-62d5-4e92-8672-33ac76ae1fa0", people[4])
            assertEquals("https://ghibliapi.herokuapp.com/people/e08880d0-6938-44f3-b179-81947e7873fc", people[5])
            assertEquals("https://ghibliapi.herokuapp.com/people/2a1dad70-802a-459d-8cc2-4ebd8821248b", people[6])
            assertEquals(1, species.size)
            assertEquals("https://ghibliapi.herokuapp.com/species/af3910a6-429f-4c74-9ad5-dfe1c4aa04f2", species[0])
            assertEquals(1, locations.size)
            assertEquals("https://ghibliapi.herokuapp.com/locations/", locations[0])
            assertEquals(1, vehicles.size)
            assertEquals("https://ghibliapi.herokuapp.com/vehicles/4e09b023-f650-4747-9ab9-eacf14540cfb", vehicles[0])
            assertEquals("https://ghibliapi.herokuapp.com/films/2baf70d1-42bb-4437-b551-e5fed5a87abe", url)
        }

    }

    @Test
    fun testGetMoviesErrorParseResponseOK() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(getMoviesResponseErrorParse))
        val response = movieNetworkDataSource.getFilms()
        assertEquals(true, response is Resource.Error)
        assertEquals(ErrorEntity.ApiError.ParseError, response.error)
    }

    private val getMoviesResponse = """
[
    {
        "id": "2baf70d1-42bb-4437-b551-e5fed5a87abe",
        "title": "Castle in the Sky",
        "original_title": "天空の城ラピュタ",
        "original_title_romanised": "Tenkū no shiro Rapyuta",
        "image": "https://image.tmdb.org/t/p/w600_and_h900_bestv2/npOnzAbLh6VOIu3naU5QaEcTepo.jpg",
        "movie_banner": "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        "description": "The orphan Sheeta inherited a mysterious crystal that links her to the mythical sky-kingdom of Laputa. With the help of resourceful Pazu and a rollicking band of sky pirates, she makes her way to the ruins of the once-great civilization. Sheeta and Pazu must outwit the evil Muska, who plans to use Laputa's science to make himself ruler of the world.",
        "director": "Hayao Miyazaki",
        "producer": "Isao Takahata",
        "release_date": "1986",
        "running_time": "124",
        "rt_score": "95",
        "people": [
            "https://ghibliapi.herokuapp.com/people/598f7048-74ff-41e0-92ef-87dc1ad980a9",
            "https://ghibliapi.herokuapp.com/people/fe93adf2-2f3a-4ec4-9f68-5422f1b87c01",
            "https://ghibliapi.herokuapp.com/people/3bc0b41e-3569-4d20-ae73-2da329bf0786",
            "https://ghibliapi.herokuapp.com/people/40c005ce-3725-4f15-8409-3e1b1b14b583",
            "https://ghibliapi.herokuapp.com/people/5c83c12a-62d5-4e92-8672-33ac76ae1fa0",
            "https://ghibliapi.herokuapp.com/people/e08880d0-6938-44f3-b179-81947e7873fc",
            "https://ghibliapi.herokuapp.com/people/2a1dad70-802a-459d-8cc2-4ebd8821248b"
        ],
        "species": [
            "https://ghibliapi.herokuapp.com/species/af3910a6-429f-4c74-9ad5-dfe1c4aa04f2"
        ],
        "locations": [
            "https://ghibliapi.herokuapp.com/locations/"
        ],
        "vehicles": [
            "https://ghibliapi.herokuapp.com/vehicles/4e09b023-f650-4747-9ab9-eacf14540cfb"
        ],
        "url": "https://ghibliapi.herokuapp.com/films/2baf70d1-42bb-4437-b551-e5fed5a87abe"
    },
    {
        "id": "12cfb892-aac0-4c5b-94af-521852e46d6a",
        "title": "Grave of the Fireflies",
        "original_title": "火垂るの墓",
        "original_title_romanised": "Hotaru no haka",
        "image": "https://image.tmdb.org/t/p/w600_and_h900_bestv2/qG3RYlIVpTYclR9TYIsy8p7m7AT.jpg",
        "movie_banner": "https://image.tmdb.org/t/p/original/vkZSd0Lp8iCVBGpFH9L7LzLusjS.jpg",
        "description": "In the latter part of World War II, a boy and his sister, orphaned when their mother is killed in the firebombing of Tokyo, are left to survive on their own in what remains of civilian life in Japan. The plot follows this boy and his sister as they do their best to survive in the Japanese countryside, battling hunger, prejudice, and pride in their own quiet, personal battle.",
        "director": "Isao Takahata",
        "producer": "Toru Hara",
        "release_date": "1988",
        "running_time": "89",
        "rt_score": "97",
        "people": [
            "https://ghibliapi.herokuapp.com/people/"
        ],
        "species": [
            "https://ghibliapi.herokuapp.com/species/af3910a6-429f-4c74-9ad5-dfe1c4aa04f2"
        ],
        "locations": [
            "https://ghibliapi.herokuapp.com/locations/"
        ],
        "vehicles": [
            "https://ghibliapi.herokuapp.com/vehicles/"
        ],
        "url": "https://ghibliapi.herokuapp.com/films/12cfb892-aac0-4c5b-94af-521852e46d6a"
    },
    {
        "id": "58611129-2dbc-4a81-a72f-77ddfc1b1b49",
        "title": "My Neighbor Totoro",
        "original_title": "となりのトトロ",
        "original_title_romanised": "Tonari no Totoro",
        "image": "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rtGDOeG9LzoerkDGZF9dnVeLppL.jpg",
        "movie_banner": "https://image.tmdb.org/t/p/original/etqr6fOOCXQOgwrQXaKwenTSuzx.jpg",
        "description": "Two sisters move to the country with their father in order to be closer to their hospitalized mother, and discover the surrounding trees are inhabited by Totoros, magical spirits of the forest. When the youngest runs away from home, the older sister seeks help from the spirits to find her.",
        "director": "Hayao Miyazaki",
        "producer": "Hayao Miyazaki",
        "release_date": "1988",
        "running_time": "86",
        "rt_score": "93",
        "people": [
            "https://ghibliapi.herokuapp.com/people/986faac6-67e3-4fb8-a9ee-bad077c2e7fe",
            "https://ghibliapi.herokuapp.com/people/d5df3c04-f355-4038-833c-83bd3502b6b9",
            "https://ghibliapi.herokuapp.com/people/3031caa8-eb1a-41c6-ab93-dd091b541e11",
            "https://ghibliapi.herokuapp.com/people/87b68b97-3774-495b-bf80-495a5f3e672d",
            "https://ghibliapi.herokuapp.com/people/d39deecb-2bd0-4770-8b45-485f26e1381f",
            "https://ghibliapi.herokuapp.com/people/591524bc-04fe-4e60-8d61-2425e42ffb2a",
            "https://ghibliapi.herokuapp.com/people/c491755a-407d-4d6e-b58a-240ec78b5061",
            "https://ghibliapi.herokuapp.com/people/f467e18e-3694-409f-bdb3-be891ade1106",
            "https://ghibliapi.herokuapp.com/people/08ffbce4-7f94-476a-95bc-76d3c3969c19",
            "https://ghibliapi.herokuapp.com/people/0f8ef701-b4c7-4f15-bd15-368c7fe38d0a"
        ],
        "species": [
            "https://ghibliapi.herokuapp.com/species/af3910a6-429f-4c74-9ad5-dfe1c4aa04f2",
            "https://ghibliapi.herokuapp.com/species/603428ba-8a86-4b0b-a9f1-65df6abef3d3",
            "https://ghibliapi.herokuapp.com/species/74b7f547-1577-4430-806c-c358c8b6bcf5"
        ],
        "locations": [
            "https://ghibliapi.herokuapp.com/locations/"
        ],
        "vehicles": [
            "https://ghibliapi.herokuapp.com/vehicles/"
        ],
        "url": "https://ghibliapi.herokuapp.com/films/58611129-2dbc-4a81-a72f-77ddfc1b1b49"
    }
]
    """

    private val getMoviesResponseErrorParse = """
[
    {
        "id": "2baf70d1-42bb-4437-b551-e5fed5a87abe",
        "title": "Castle in the Sky",
        "original_title": "天空の城ラピュタ",
        "original_titleromanised": "Tenkū no shiro Rapyuta",
        "image": "https://image.tmdb.org/t/p/w600_and_h900_bestv2/npOnzAbLh6VOIu3naU5QaEcTepo.jpg",
        "movie_banner": "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        "description": "The orphan Sheeta inherited a mysterious crystal that links her to the mythical sky-kingdom of Laputa. With the help of resourceful Pazu and a rollicking band of sky pirates, she makes her way to the ruins of the once-great civilization. Sheeta and Pazu must outwit the evil Muska, who plans to use Laputa's science to make himself ruler of the world.",
        "director": "Hayao Miyazaki",
        "producer": "Isao Takahata",
        "release_date": "1986",
        "running_time": "124",
        "rt_score": "95",
        "people": [
            "https://ghibliapi.herokuapp.com/people/598f7048-74ff-41e0-92ef-87dc1ad980a9",
            "https://ghibliapi.herokuapp.com/people/fe93adf2-2f3a-4ec4-9f68-5422f1b87c01",
            "https://ghibliapi.herokuapp.com/people/3bc0b41e-3569-4d20-ae73-2da329bf0786",
            "https://ghibliapi.herokuapp.com/people/40c005ce-3725-4f15-8409-3e1b1b14b583",
            "https://ghibliapi.herokuapp.com/people/5c83c12a-62d5-4e92-8672-33ac76ae1fa0",
            "https://ghibliapi.herokuapp.com/people/e08880d0-6938-44f3-b179-81947e7873fc",
            "https://ghibliapi.herokuapp.com/people/2a1dad70-802a-459d-8cc2-4ebd8821248b"
        ],
        "species": [
            "https://ghibliapi.herokuapp.com/species/af3910a6-429f-4c74-9ad5-dfe1c4aa04f2"
        ],
        "locations": [
            "https://ghibliapi.herokuapp.com/locations/"
        ],
        "vehicles": [
            "https://ghibliapi.herokuapp.com/vehicles/4e09b023-f650-4747-9ab9-eacf14540cfb"
        ],
        "url": "https://ghibliapi.herokuapp.com/films/2baf70d1-42bb-4437-b551-e5fed5a87abe"
    }
]
    """
}

