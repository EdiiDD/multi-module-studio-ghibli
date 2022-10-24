package com.edy.core_navigation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.edy.core_error.model.ErrorEntity
import com.edy.core_navigation.R
import com.edy.core_navigation.ui.connection.ConnectivityObserver
import com.edy.core_navigation.ui.connection.NetworkConnectivityObserver
import kotlinx.coroutines.launch

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit) = {},
) {
    val context = LocalContext.current
    var visible by remember { mutableStateOf(false) }
    val status by NetworkConnectivityObserver(context).observe().collectAsState(initial = ConnectivityObserver.Status.Unavailable)

    var messageStatusConnection: String

    LaunchedEffect(status) {
        visible = when (status) {
            ConnectivityObserver.Status.Available -> {
                false
            }
            ConnectivityObserver.Status.Unavailable,
            ConnectivityObserver.Status.Losing,
            ConnectivityObserver.Status.Lost,
            -> {
                true
            }
        }
    }

    Box(modifier = modifier) {
        content()
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(3_500)),
            exit = fadeOut(animationSpec = tween(7_500))
        ) {
            messageStatusConnection = when (status) {
                ConnectivityObserver.Status.Available -> {
                    stringResource(R.string.enable_network)
                }
                ConnectivityObserver.Status.Unavailable,
                ConnectivityObserver.Status.Losing,
                ConnectivityObserver.Status.Lost,
                -> {
                    stringResource(R.string.error_network)
                }
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Text(text = messageStatusConnection,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                        .background(Color.Black)
                        .padding(12.dp),
                    color = Color.Green,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
}


@Composable
fun BaseErrorScreen(
    error: ErrorEntity,
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        val string = when (error) {
            ErrorEntity.ApiError.BadRequest,
            ErrorEntity.ApiError.ParseError,
            ErrorEntity.ApiError.ServerError,
            ErrorEntity.ApiError.ServiceUnavailable,
            ErrorEntity.ApiError.NotFound,
            -> stringResource(R.string.error_api_notified)
            ErrorEntity.ApiError.ConnectivityError -> stringResource(R.string.error_api_connectivity_error)
            ErrorEntity.ApiError.TimeOutError -> stringResource(R.string.error_api_try_again)
            is ErrorEntity.ApiError.UnauthorizedError -> stringResource(R.string.error_api_unauthorized_error)


            ErrorEntity.FileError.NotFound -> stringResource(R.string.error_file_not_found)
            ErrorEntity.FileError.ReadError -> stringResource(R.string.error_file_read_error)
            ErrorEntity.LocalDataBaseError.MissingLocalStorageError -> stringResource(R.string.error_local_database_missing)
            ErrorEntity.Unknown -> stringResource(R.string.error_api_notified)
        }

        Text(text = string,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 12.dp))
    }
}

@Composable
fun BaseLoadingScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(composition)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        LottieAnimation(
            composition = composition,
            progress = progress,
        )
    }
}

@Preview
@Composable
fun BaseScreenPreview() {
    BaseScreen(content = {
        Text(
            text = "Base Screen",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
        )
    })
}

@Preview
@Composable
fun BaseErrorScreenPreview() {
    BaseErrorScreen(error = ErrorEntity.ApiError.ConnectivityError)
}

@Preview
@Composable
fun BaseLoadingScreenPreview() {
    BaseLoadingScreen()
}