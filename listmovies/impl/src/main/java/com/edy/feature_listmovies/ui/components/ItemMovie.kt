package com.edy.feature_listmovies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ItemMovie(
    modifier: Modifier = Modifier,
    image: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    year: @Composable (() -> Unit)? = null,
    score: @Composable (() -> Unit)? = null,
    scoreTextSize: Dp = 20.dp,
) {

    Box(
        modifier = modifier
    ) {
        image?.let {
            Column(
                modifier = Modifier
                    .padding(top = score?.let { scoreTextSize / 2f } ?: 0.dp)
            ) {
                image()
            }
        }

        title?.let {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .graphicsLayer { alpha = 0.99F }
                    .drawWithContent {
                        val colors = listOf(Color.Transparent, Color.Black)
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors),
                            blendMode = BlendMode.DstOver
                        )
                    }
                    .padding(12.dp)
            ) {
                title()
                year?.let {
                    year()
                }
            }
        }

        score?.let {
            Row(
                modifier = Modifier
                    .height(scoreTextSize)
                    .align(Alignment.TopCenter),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                score()
            }
        }
    }
}

@Preview
@Composable
fun ItemMoviePreview() {
    ItemMovie(
        score = {
            Text(
                text = "97",
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(4.dp),
                color = Color.Black,
                style = MaterialTheme.typography.overline,
                textAlign = TextAlign.Center,
            )
        },
        image = {
            Image(
                painter = rememberAsyncImagePainter("https://example.com/image.jpg"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Yellow)
            )
        },
        title = {
            Text(
                text = "‘El viaje de Chihiro’",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        year = {
            Text(
                text = "2001",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                style = MaterialTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = Modifier
            .width(175.dp)
            .height(250.dp)
            .background(Color.Transparent)
    )
}


@Preview
@Composable
fun ItemMoviePreview1() {
    ItemMovie(
        image = {
            Image(
                painter = rememberAsyncImagePainter("https://example.com/image.jpg"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )
        },
        title = {
            Text(
                text = "‘El viaje de Chihiro’",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        year = {
            Text(
                text = "2001",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.White,
                style = MaterialTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = Modifier
            .width(175.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Transparent)
    )
}