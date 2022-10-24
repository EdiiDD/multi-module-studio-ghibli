package com.edy.core_navigation.di

import android.content.Context
import androidx.compose.runtime.compositionLocalOf


interface NavigationProvider {

    val context: Context
}

val LocalNavigationProvider = compositionLocalOf<NavigationProvider> { error("No common provider found!") }