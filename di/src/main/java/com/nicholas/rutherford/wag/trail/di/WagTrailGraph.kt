package com.nicholas.rutherford.wag.trail.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph

/**
 * Defines the [DependencyGraph] under a given scope.
 * Right now only the [WagTrailViewModelFactory] is defined to be used
 */
@DependencyGraph(AppScope::class)
interface WagTrailGraph {
    val viewModelFactory: WagTrailViewModelFactory
}
