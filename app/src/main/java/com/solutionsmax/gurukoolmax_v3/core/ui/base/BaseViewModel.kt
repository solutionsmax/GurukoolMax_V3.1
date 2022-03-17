package com.solutionsmax.gurukoolmax_v3.core.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.functional.SingleLiveEvent
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    private val _errorLivedata: SingleLiveEvent<Event<Failure>> = SingleLiveEvent()
    val errorLiveData: LiveData<Event<Failure>>
        get() = _errorLivedata

    private val viewModelJob = SupervisorJob()

    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    protected fun launchIOCoroutine(block: suspend CoroutineScope.() -> Unit): Job =
        ioScope.launch(block = block)

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    protected open fun postError(error: Failure) {
        _errorLivedata.postValue(Event(error))
    }
}