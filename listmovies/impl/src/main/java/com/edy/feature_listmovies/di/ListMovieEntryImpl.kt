package com.edy.feature_listmovies.di

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.edy.api.ListMovieEntry
import com.edy.core_navigation.di.Destinations
import com.edy.feature_listmovies.ui.screen.ListMovieScreen
import javax.inject.Inject

class ListMovieEntryImpl @Inject constructor() : ListMovieEntry() {

    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
        destinations: Destinations,
    ) {
        composable(route = featureRoute, arguments){
            ListMovieScreen(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
    }
}