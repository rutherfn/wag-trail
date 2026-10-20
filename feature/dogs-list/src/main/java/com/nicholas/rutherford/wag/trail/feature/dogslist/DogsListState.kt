package com.nicholas.rutherford.wag.trail.feature.dogslist

/**
 * UI state for [DogsListScreen].
 *
 * An empty [dogs] list is the first-run empty state. The list stays empty until a later
 * change starts saving dogs.
 */
data class DogsListState(
    val dogs: List<String> = emptyList(),
)
