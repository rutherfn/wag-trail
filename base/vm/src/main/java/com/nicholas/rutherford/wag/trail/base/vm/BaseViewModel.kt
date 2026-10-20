package com.nicholas.rutherford.wag.trail.base.vm

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * ViewModel base that logs lifecycle events and collects flows only while the screen is active.
 *
 * Subclasses can override [getScope] to enable [collectFlow]. Collection stays paused until the
 * trigger from [getFlowCollectionTrigger] is reached, and every job is canceled in [onCleared].
 */
abstract class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    private val activeFlowCollections = mutableListOf<Job>()
    private var isResumed = false
    private var isStart = false

    /**
     * Scope used for lifecycle-aware collection. Returning null skips collection so a subclass
     * can stay inert until it opts in.
     */
    protected open fun getScope(): CoroutineScope? = null

    protected open fun getFlowCollectionTrigger(): FlowCollectionTrigger = FlowCollectionTrigger.RESUME

    protected fun <T> collectFlow(
        flow: Flow<T>,
        onCollect: suspend (T) -> Unit,
    ) {
        getScope()?.let { scope ->
            val job = scope.launch {
                flow.collectLatest { value ->
                    if (shouldCollectFlow()) {
                        onCollect(value)
                    }
                }
            }
            activeFlowCollections.add(job)
        }
    }

    protected fun <T1, T2> collectFlows(
        flow1: Flow<T1>,
        flow2: Flow<T2>,
        onCollect: suspend (T1, T2) -> Unit,
    ) {
        getScope()?.let { scope ->
            val job = scope.launch {
                combine(flow1, flow2) { first, second ->
                    if (shouldCollectFlow()) {
                        onCollect(first, second)
                    }
                }.collectLatest { }
            }
            activeFlowCollections.add(job)
        }
    }

    protected fun <T1, T2, T3, T4> collectFlows(
        flow1: Flow<T1>,
        flow2: Flow<T2>,
        flow3: Flow<T3>,
        flow4: Flow<T4>,
        onCollect: suspend (T1, T2, T3, T4) -> Unit,
    ) {
        getScope()?.let { scope ->
            val job = scope.launch {
                combine(flow1, flow2, flow3, flow4) { first, second, third, fourth ->
                    if (shouldCollectFlow()) {
                        onCollect(first, second, third, fourth)
                    }
                }.collectLatest { }
            }
            activeFlowCollections.add(job)
        }
    }

    private fun shouldCollectFlow(): Boolean {
        return when (getFlowCollectionTrigger()) {
            FlowCollectionTrigger.INIT -> true
            FlowCollectionTrigger.START -> isStart
            FlowCollectionTrigger.RESUME -> isResumed
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.d(TAG, "${this::class.simpleName} → onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        isStart = true
        Log.d(TAG, "${this::class.simpleName} → onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        isResumed = true
        Log.d(TAG, "${this::class.simpleName} → onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        isResumed = false
        Log.d(TAG, "${this::class.simpleName} → onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        isStart = false
        Log.d(TAG, "${this::class.simpleName} → onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.d(TAG, "${this::class.simpleName} → onDestroy")
    }

    override fun onCleared() {
        activeFlowCollections.forEach { it.cancel() }
        activeFlowCollections.clear()
        Log.d(TAG, "${this::class.simpleName} → onCleared")
    }

    private companion object {
        const val TAG = "BaseViewModel"
    }
}
