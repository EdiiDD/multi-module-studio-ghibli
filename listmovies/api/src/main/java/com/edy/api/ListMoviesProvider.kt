package com.edy.api

import androidx.compose.runtime.compositionLocalOf

interface ListMoviesProvider

val LocalProfileProvider = compositionLocalOf<ListMoviesProvider> {
    error("No profile provider found!")
}