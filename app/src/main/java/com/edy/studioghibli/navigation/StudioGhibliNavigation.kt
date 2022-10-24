package com.edy.studioghibli.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.edy.api.ListMovieEntry
import com.edy.core_navigation.di.find
import com.edy.studioghibli.di.LocalAppProvider

@Composable
fun StudioGhibliNavigation(
    navController: NavHostController,
) {
    val destinations = LocalAppProvider.current.destinations

    val imagesScreen = destinations.find<ListMovieEntry>()

    NavHost(
        navController = navController,
        startDestination = imagesScreen.featureRoute
    ) {
        with(imagesScreen){
            navigation(navController, destinations)
        }
    }
}