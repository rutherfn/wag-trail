package com.nicholas.rutherford.wag.trail.base.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

/**
 * Attaches [viewModel] to [lifecycleOwner] for the composition, then removes it on dispose.
 *
 * @param viewModel Returns back the actual [BaseViewModel] that gets added to lifecycle observer
 * @param lifecycleOwner Default [LifecycleOwner] that tales on a viewModel to manage
 */
@Composable
fun ObserveLifecycle(
    viewModel: BaseViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    DisposableEffect(key1 = lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(viewModel)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(viewModel)
        }
    }
}
