package com.irfanirawansukirman.belajarunittest

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {
    open val Main: CoroutineContext by lazy { Dispatchers.Main }
    open val Io: CoroutineContext by lazy { Dispatchers.IO }
}