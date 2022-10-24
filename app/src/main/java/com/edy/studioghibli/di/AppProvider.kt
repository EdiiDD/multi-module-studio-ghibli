package com.edy.studioghibli.di

import androidx.compose.runtime.compositionLocalOf
import com.edy.core_navigation.di.Destinations


interface AppProvider  {

    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }