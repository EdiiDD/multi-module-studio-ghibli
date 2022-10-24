package com.edy.studioghibli.navigation

import androidx.navigation.NamedNavArgument

sealed class StudioGhibliDestionations(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {

    // home screen
    object ListMovies : StudioGhibliDestionations("listmovies")
}