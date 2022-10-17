package com.ascri.cleanapp.utils.helpers

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SingleRunner {
    /**
     * A coroutine mutex implements a lock that may only be taken by one coroutine at a time.
     */
    private val mutex = Mutex()
    suspend fun <T> afterPrevious(block: suspend () -> T): T {
        mutex.withLock {
            return block()
        }
    }
}