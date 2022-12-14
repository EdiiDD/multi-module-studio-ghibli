package com.edy.core_navigation.di

import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
annotation class FeatureEntryKey(val value: KClass<out FeatureEntry>)