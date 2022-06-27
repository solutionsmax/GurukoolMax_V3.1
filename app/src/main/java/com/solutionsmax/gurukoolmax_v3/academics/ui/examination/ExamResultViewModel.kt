package com.solutionsmax.gurukoolmax_v3.academics.ui.examination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_result.RetrieveExamResultsItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.CheckDuplicateExamResultParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PostExamResultParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveExamResultDetailsParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveExamResultListParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.examination.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ExamResultViewModel @Inject constructor(
    private val postExamResultUseCase: PostExamResultUseCase,
    private val amendExamResultUseCase: AmendExamResultUseCase,
    private val checkDuplicateExamResultUseCase: CheckDuplicateExamResultUseCase,
    private val retrieveExamResultDetailsUseCase: RetrieveExamResultDetailsUseCase,
    private val retrieveExamResultDetailsListUseCase: RetrieveExamResultDetailsListUseCase
) : BaseViewModel() {

    private val _postMutableExamResultData: MutableLiveData<Int> = MutableLiveData()
    val postMutableExamResultData: LiveData<Int>
        get() = _postMutableExamResultData

    private val _amendMutableExamResultData: MutableLiveData<Int> = MutableLiveData()
    val amendMutableExamResultData: LiveData<Int>
        get() = _amendMutableExamResultData

    private val _checkDuplicateMutableExamResultData: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateMutableExamResultData: LiveData<Int>
        get() = _checkDuplicateMutableExamResultData

    private val _retrieveDetailsMutableExamResult: MutableLiveData<List<RetrieveExamResultsItems>> =
        MutableLiveData()
    val retrieveDetailsMutableExamResult: LiveData<List<RetrieveExamResultsItems>>
        get() = _retrieveDetailsMutableExamResult

    private val _retrieveListMutableExamResult: MutableLiveData<List<RetrieveExamResultsItems>> =
        MutableLiveData()
    val retrieveListMutableExamResult: LiveData<List<RetrieveExamResultsItems>>
        get() = _retrieveListMutableExamResult

    fun postExamResult(postExamResultParams: PostExamResultParams) = launchIOCoroutine {
        postExamResultUseCase(postExamResultParams).fold(
            {
                postError(it)
            },
            {
                _postMutableExamResultData.postValue(it)
            }
        )
    }

    fun amendExamResult(postExamResultParams: PostExamResultParams) = launchIOCoroutine {
        amendExamResultUseCase(postExamResultParams).fold(
            {
                postError(it)
            },
            {
                _amendMutableExamResultData.postValue(it)
            }
        )
    }

    fun checkDuplicateExamResult(checkDuplicateExamResultParams: CheckDuplicateExamResultParams) =
        launchIOCoroutine {
            checkDuplicateExamResultUseCase(checkDuplicateExamResultParams).fold(
                {
                    postError(it)
                },
                {
                    _checkDuplicateMutableExamResultData.postValue(it)
                }
            )
        }

    fun retrieveExamResultDetails(retrieveExamResultDetailsParams: RetrieveExamResultDetailsParams) =
        launchIOCoroutine {
            retrieveExamResultDetailsUseCase(retrieveExamResultDetailsParams).fold(
                {
                    postError(it)
                },
                {
                    _retrieveDetailsMutableExamResult.postValue(it)
                }
            )
        }

    fun retrieveExamResultList(retrieveExamResultListParams: RetrieveExamResultListParams) =
        launchIOCoroutine {
            retrieveExamResultDetailsListUseCase(retrieveExamResultListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _retrieveListMutableExamResult.postValue(result)
                    }
                )
            }
        }
}