package com.solutionsmax.gurukoolmax_v3.academics.ui.examination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.PopulateExamScheduleListItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_schedule.RetrieveExamScheduleItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.examination.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ExaminationScheduleViewModel @Inject constructor(
    private val postExamScheduleInfoUseCase: PostExamScheduleInfoUseCase,
    private val amendExamScheduleInfoUseCase: AmendExamScheduleInfoUseCase,
    private val setStatusExamScheduleUseCase: SetStatusExamScheduleUseCase,
    private val checkDuplicateExamScheduleUseCase: CheckDuplicateExamScheduleUseCase,
    private val populateExamScheduleListUseCase: PopulateExamScheduleListUseCase,
    private val retrieveExamScheduleDetailsUseCase: RetrieveExamScheduleDetailsUseCase,
    private val retrieveExamScheduleListUseCase: RetrieveExamScheduleListUseCase
) : BaseViewModel() {

    private val _postMutableExamSchedule: MutableLiveData<Int> = MutableLiveData()
    val postMutableExamSchedule: LiveData<Int>
        get() = _postMutableExamSchedule

    private val _amendMutableExamSchedule: MutableLiveData<Int> = MutableLiveData()
    val amendMutableExamSchedule: LiveData<Int>
        get() = _amendMutableExamSchedule

    private val _setStatusMutableExamSchedule: MutableLiveData<Int> = MutableLiveData()
    val setStatusMutableExamSchedule: LiveData<Int>
        get() = _setStatusMutableExamSchedule

    private val _checkDuplicateMutableExamSchedule: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateMutableExamSchedule: LiveData<Int>
        get() = _checkDuplicateMutableExamSchedule

    private val _populateMutableExamSchedule: MutableLiveData<List<PopulateExamScheduleListItem>> =
        MutableLiveData()
    val populateMutableExamSchedule: LiveData<List<PopulateExamScheduleListItem>>
        get() = _populateMutableExamSchedule

    private val _retrieveDetailsMutableExamSchedule: MutableLiveData<List<RetrieveExamScheduleItems>> =
        MutableLiveData()
    val retrieveDetailsMutableExamSchedule: LiveData<List<RetrieveExamScheduleItems>>
        get() = _retrieveDetailsMutableExamSchedule

    private val _retrieveListMutableExamSchedule: MutableLiveData<List<RetrieveExamScheduleItems>> =
        MutableLiveData()
    val retrieveListMutableExamSchedule: LiveData<List<RetrieveExamScheduleItems>>
        get() = _retrieveListMutableExamSchedule

    fun postExamSchedule(postExamScheduleParams: PostExamScheduleParams) = launchIOCoroutine {
        postExamScheduleInfoUseCase(postExamScheduleParams).fold(
            {
                postError(it)
            },
            {
                _postMutableExamSchedule.postValue(it)
            }
        )
    }

    fun amendExamSchedule(postExamScheduleParams: PostExamScheduleParams) = launchIOCoroutine {
        amendExamScheduleInfoUseCase(postExamScheduleParams).fold(
            {
                postError(it)
            },
            {
                _amendMutableExamSchedule.postValue(it)
            }
        )
    }

    fun setStatusExamSchedule(setStatusExamScheduleParams: SetStatusExamScheduleParams) =
        launchIOCoroutine {
            setStatusExamScheduleUseCase(setStatusExamScheduleParams).fold(
                {
                    postError(it)
                },
                {
                    _setStatusMutableExamSchedule.postValue(it)
                }
            )
        }

    fun checkDuplicateExamSchedule(checkDuplicateExamScheduleParams: CheckDuplicateExamScheduleParams) =
        launchIOCoroutine {
            checkDuplicateExamScheduleUseCase(checkDuplicateExamScheduleParams).fold(
                {
                    postError(it)
                },
                {
                    _checkDuplicateMutableExamSchedule.postValue(it)
                }
            )
        }

    fun populateExamSchedule(populateExamScheduleParams: PopulateExamScheduleParams) =
        launchIOCoroutine {
            populateExamScheduleListUseCase(populateExamScheduleParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _populateMutableExamSchedule.postValue(result)
                    }
                )
            }
        }

    fun retrieveExamScheduleDetails(retrieveDetailsExamScheduleParams: RetrieveDetailsExamScheduleParams) =
        launchIOCoroutine {
            retrieveExamScheduleDetailsUseCase(retrieveDetailsExamScheduleParams).fold(
                {
                    postError(it)
                },
                {
                    _retrieveDetailsMutableExamSchedule.postValue(it)
                }
            )
        }

    fun retrieveExamScheduleList(retrieveListExamScheduleParams: RetrieveListExamScheduleParams) =
        launchIOCoroutine {
            retrieveExamScheduleListUseCase(retrieveListExamScheduleParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _retrieveListMutableExamSchedule.postValue(result)
                    }
                )
            }
        }
}