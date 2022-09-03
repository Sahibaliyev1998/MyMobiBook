package com.sahibaliyev.mymobibook.MVVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseMVVM(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}