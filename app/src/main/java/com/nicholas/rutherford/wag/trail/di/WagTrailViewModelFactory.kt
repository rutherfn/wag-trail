package com.nicholas.rutherford.wag.trail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.Provider
import dev.zacsweers.metro.SingleIn
import kotlin.reflect.KClass

/**
 * App-scoped [ViewModelProvider.Factory] that creates ViewModels from Metro's multi binding map.
 *
 * Feature ViewModels register with `ContributesIntoMap` and `ViewModelKey`. [create] looks up
 * modelClass in [viewModelProviders] and returns a new instance from the matching [Provider].
 * A class that was never registered throws [IllegalArgumentException].
 *
 * @param viewModelProviders ViewModel classes mapped to the providers that construct them.
 */
@Inject
@SingleIn(AppScope::class)
class WagTrailViewModelFactory(
    private val viewModelProviders: Map<KClass<out ViewModel>, Provider<ViewModel>>,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val provider = viewModelProviders[modelClass.kotlin]
            ?: throw IllegalArgumentException("Unknown model class $modelClass")
        @Suppress("UNCHECKED_CAST")
        return provider() as T
    }
}
