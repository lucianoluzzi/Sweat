package com.lucianoluzzi.tests

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherRegistry {

    private val defaultHolder = DispatcherHolder()

    private var currentHolder = defaultHolder

    /**
     * Proxy for [Dispatchers.Main].
     */
    val Main: CoroutineDispatcher
        get() = currentHolder.main

    /**
     * Proxy for [Dispatchers.IO].
     */
    val IO: CoroutineDispatcher
        get() = currentHolder.io

    /**
     * Proxy for [Dispatchers.Default]
     */
    val Computation: CoroutineDispatcher
        get() = currentHolder.computation

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setMain(dispatcher: CoroutineDispatcher) {
        currentHolder = currentHolder.copy(main = dispatcher)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setIO(dispatcher: CoroutineDispatcher) {
        currentHolder = currentHolder.copy(io = dispatcher)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setComputation(dispatcher: CoroutineDispatcher) {
        currentHolder = currentHolder.copy(computation = dispatcher)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun reset() {
        currentHolder = defaultHolder
    }

    private data class DispatcherHolder(
        val main: CoroutineDispatcher = Dispatchers.Main,
        val io: CoroutineDispatcher = Dispatchers.IO,
        val computation: CoroutineDispatcher = Dispatchers.Default
    )
}
