package com.edy.studioghibli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.edy.studioghibli.di.LocalAppProvider
import com.edy.studioghibli.navigation.StudioGhibliNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CompositionLocalProvider(
                LocalAppProvider provides application.appProvider
            ) {
                StudioGhibliNavigation(navController)
            }
        }
    }
}
