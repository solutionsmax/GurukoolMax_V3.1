package com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.remote.entity.PostErrorLogsParams
import com.solutionsmax.gurukoolmax_v3.remote.interactor.PostErrorLogsUseCase
import javax.inject.Inject

class ErrorLogsViewModel @Inject constructor(
    private val postErrorLogsUseCase: PostErrorLogsUseCase
) : BaseViewModel() {

    private val _postErrorLogsMutableData: MutableLiveData<Int> = MutableLiveData()
    val postErrorLogsMutableData: LiveData<Int>
        get() = _postErrorLogsMutableData

    fun postErrorLogs(url: String, sAuthorization: String, postErrorLogsItems: PostErrorLogsItems) =
        launchIOCoroutine {
            postErrorLogsUseCase(
                PostErrorLogsParams(
                    url = url,
                    sAuthorization = sAuthorization,
                    postErrorLogsItems = postErrorLogsItems
                )
            ).fold(
                {
                    postError(it)
                },
                {
                    _postErrorLogsMutableData.postValue(it)
                }
            )
        }
}