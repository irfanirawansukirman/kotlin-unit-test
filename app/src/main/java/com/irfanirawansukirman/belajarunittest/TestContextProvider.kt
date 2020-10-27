package com.irfanirawansukirman.belajarunittest

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val Main: CoroutineContext
        get() = Dispatchers.Unconfined

    override val Io: CoroutineContext
        get() = Dispatchers.Unconfined
}