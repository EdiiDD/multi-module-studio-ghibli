package com.edy.feature_listmovies.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.edy.core_navigation.ui.BaseErrorScreen
import com.edy.core_navigation.ui.BaseLoadingScreen
import com.edy.core_navigation.ui.BaseScreen
import com.edy.feature_listmovies.R
import com.edy.feature_listmovies.ui.ListMovieViewModel
import com.edy.feature_listmovies.ui.ListMoviesUiState
import com.edy.feature_listmovies.ui.MovieState
import com.edy.feature_listmovies.ui.WrappedMovieStateList
import com.edy.feature_listmovies.ui.components.ItemMovie


@Composable
fun ListMovieScreen(
    navController: NavHostController,
    viewModel: ListMovieViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    BaseScreen(
        content = {
            ListMovieWrapperScreen(
                uiState = uiState,
                onClickMovie = viewModel::onClickItem,
                onPagination = viewModel::getPaginationMovieList
            )
        }
    )
}

@Composable
fun ListMovieWrapperScreen(
    uiState: ListMoviesUiState,
    onClickMovie: (String) -> Unit,
    onPagination: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        if (uiState.isLoading) {
            BaseLoadingScreen()
        }

        uiState.error?.let { error ->
            BaseErrorScreen(error = error)
        }

        if (!uiState.isLoading && uiState.error == null) {
            ListMovieSuccessScreen(
                bestMovies = uiState.bestMovies,
                favoritesMovies = uiState.favoritesMovies,
                isLoadingPagination = uiState.isLoadingPagination,
                onClickMovie = {
                    onClickMovie(it)
                },
                onPagination = {
                    onPagination()
                }
            )
        }
    }
}

@Composable
fun ListMovieSuccessScreen(
    bestMovies: WrappedMovieStateList<MovieState>,
    favoritesMovies: WrappedMovieStateList<MovieState>,
    isLoadingPagination: Boolean,
    onClickMovie: (String) -> Unit,
    onPagination: () -> Unit,
) {


    Column(
        modifier = Modifier.padding(
            start = 12.dp,
            top = 12.dp,
            end = 12.dp,
        )) {

        FavoriteMoviesScreen(
            favoritesMovies = favoritesMovies,
            modifier = Modifier.fillMaxWidth()
        )

        BestMoviesScreen(
            bestMovies = bestMovies,
            modifier = Modifier.fillMaxSize(),
            isLoadingPagination = isLoadingPagination,
            onClickMovie = {
                onClickMovie(it)
            },
            onPagination = {
                onPagination()
            }
        )
    }
}

@Composable
fun FavoriteMoviesScreen(
    favoritesMovies: WrappedMovieStateList<MovieState>,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(favoritesMovies.list.isNotEmpty()) }
    val shapeSmallItemMovie = RoundedCornerShape(12.dp)

    LaunchedEffect(favoritesMovies.list) {
        visible = favoritesMovies.list.isNotEmpty()
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(450)),
        exit = fadeOut(animationSpec = tween(
            durationMillis = 450,
            delayMillis = 175
        ))
    ) {
        Column(
            modifier = modifier

        ) {

            Text(
                text = stringResource(R.string.favorite_movies),
                modifier = Modifier,
                color = Color.Black,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
            )

            LazyHorizontalGrid(contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                rows = GridCells.Fixed(1),
                content = {
                    items(
                        favoritesMovies.list.size,
                        key = {
                            it
                        }
                    ) { index ->
                        with(favoritesMovies.list[index]) {
                            ItemMovie(modifier = Modifier
                                .width(175.dp)
                                .height(50.dp)
                                .clip(shapeSmallItemMovie)
                                .background(Color.Transparent), image = {
                                Image(painter = rememberAsyncImagePainter(picture),
                                    contentDescription = title,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop)
                            }, title = {
                                Text(text = title,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            }, year = {
                                Text(text = releaseDate,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    color = Color.White,
                                    style = MaterialTheme.typography.body2,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            })
                        }
                    }
                },
                modifier = Modifier.height(75.dp))
        }
    }
}

@Composable
fun BestMoviesScreen(
    bestMovies: WrappedMovieStateList<MovieState>,
    isLoadingPagination: Boolean,
    onClickMovie: (String) -> Unit,
    onPagination: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val bestMoviesListState = rememberLazyGridState()

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.best_movies),
            modifier = Modifier,
            color = Color.Black,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
        )

        Box {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                modifier = Modifier.padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                state = bestMoviesListState,
                content = {
                    items(bestMovies.list.size) { index ->
                        if (index >= bestMovies.list.size - 1 && !isLoadingPagination) {
                            LaunchedEffect(Unit) {
                                onPagination()
                            }
                        }
                        with(bestMovies.list[index]) {
                            ItemMovie(score = {
                                Text(
                                    text = "Score: $score",
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
                                        .padding(4.dp),
                                    color = Color.White,
                                    style = MaterialTheme.typography.overline,
                                    textAlign = TextAlign.Center,
                                )
                            }, image = {
                                Image(painter = rememberAsyncImagePainter(picture),
                                    contentDescription = title,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(25.dp))
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop)
                            }, title = {
                                Text(text = title,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    color = Color.White,
                                    style = MaterialTheme.typography.body1,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            }, year = {
                                Text(text = releaseDate,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    color = Color.White,
                                    style = MaterialTheme.typography.body2,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            }, modifier = Modifier
                                .width(175.dp)
                                .height(250.dp)
                                .clip(RoundedCornerShape(25.dp))
                                .background(Color.Transparent)
                                .clickable {
                                    onClickMovie(title)
                                })
                        }
                    }
                }
            )

            if (isLoadingPagination) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(95.dp)
                        .padding(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ListMovieSuccessScreenPreview() {
    ListMovieSuccessScreen(
        bestMovies = WrappedMovieStateList(
            list = MutableList(20) {
                MovieState(
                    title = "Title1",
                    releaseDate = "2022",
                    picture = "",
                    score = "100"
                )
            }
        ),
        favoritesMovies = WrappedMovieStateList(
            list = MutableList(20) {
                MovieState(
                    title = "Title1",
                    releaseDate = "2022",
                    picture = "",
                    score = "100"
                )
            }
        ),
        isLoadingPagination = false,
        onClickMovie = {},
        onPagination = {}
    )
}

@Preview
@Composable
fun ListMovieSuccessScreenLoadingPaginationPreview() {
    ListMovieSuccessScreen(
        bestMovies = WrappedMovieStateList(
            list = MutableList(20) {
                MovieState(
                    title = "Title1",
                    releaseDate = "2022",
                    picture = "",
                    score = "100"
                )
            }
        ),
        favoritesMovies = WrappedMovieStateList(
            list = MutableList(20) {
                MovieState(
                    title = "Title1",
                    releaseDate = "2022",
                    picture = "",
                    score = "100"
                )
            }
        ),
        isLoadingPagination = false,
        onClickMovie = {},
        onPagination = {}
    )
}

@Preview
@Composable
fun ListMovieSuccessScreenLoadingPreview() {
    ListMovieWrapperScreen(
        uiState = ListMoviesUiState(
            isLoading = true
        ),
        onClickMovie = {},
        onPagination = {}
    )
}