package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.PopulateSubjectProgramList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.subject_management.RetrieveSubjectInfoItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.subject_management.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SubjectManagementViewModel @Inject constructor(
    private val postSubjectManagementInfoUseCase: PostSubjectManagementInfoUseCase,
    private val amendSubjectManagementInfoUseCase: AmendSubjectManagementInfoUseCase,
    private val checkDuplicateSubjectManagementInfoUseCase: CheckDuplicateSubjectManagementInfoUseCase,
    private val populateSubjectListUseCase: PopulateSubjectListUseCase,
    private val retrieveSubjectListUseCase: RetrieveSubjectListUseCase,
    private val retrieveSubjectDetailsUseCase: RetrieveSubjectDetailsUseCase,
    private val populateSubjectProgramsListUseCase: PopulateSubjectProgramsListUseCase,
    private val setSubjectProgramStatusUseCase: SetSubjectProgramStatusUseCase
) : BaseViewModel() {

    private val _postMutableSubjectInfo: MutableLiveData<Int> = MutableLiveData()
    val postMutableSubjectInfo: LiveData<Int>
        get() = _postMutableSubjectInfo

    private val _amendMutableSubjectInfo: MutableLiveData<Int> = MutableLiveData()
    val amendMutableSubjectInfo: LiveData<Int>
        get() = _amendMutableSubjectInfo

    private val _checkDuplicateMutableSubjectInfo: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateMutableSubjectInfo: LiveData<Int>
        get() = _checkDuplicateMutableSubjectInfo

    private val _populateMutableSubjectList: MutableLiveData<MutableList<PopulateSubjectList>> =
        MutableLiveData()
    val populateMutableSubjectList: LiveData<MutableList<PopulateSubjectList>>
        get() = _populateMutableSubjectList

    private val _retrieveMutableSubjectList: MutableLiveData<List<RetrieveSubjectInfoItems>> =
        MutableLiveData()
    val retrieveMutableSubjectList: LiveData<List<RetrieveSubjectInfoItems>>
        get() = _retrieveMutableSubjectList

    private val _retrieveMutableSubjectDetails: MutableLiveData<List<RetrieveSubjectInfoItems>> =
        MutableLiveData()
    val retrieveMutableSubjectDetails: LiveData<List<RetrieveSubjectInfoItems>>
        get() = _retrieveMutableSubjectDetails

    private val _mutablePopulateSubjectProgramList: MutableLiveData<List<PopulateSubjectProgramList>> =
        MutableLiveData()
    val mutablePopulateSubjectProgramList: LiveData<List<PopulateSubjectProgramList>>
        get() = _mutablePopulateSubjectProgramList

    private val _mutableSetSubjectProgramStatus: MutableLiveData<Int> = MutableLiveData()
    val mutableSetSubjectProgramStatus: LiveData<Int>
        get() = _mutableSetSubjectProgramStatus

    fun postSubjectInfo(postSubjectInfoParams: PostSubjectInfoParams) = launchIOCoroutine {
        postSubjectManagementInfoUseCase(postSubjectInfoParams).fold(
            {
                postError(it)
            },
            {
                _postMutableSubjectInfo.postValue(it)
            }
        )
    }

    fun amendSubjectInfo(postSubjectInfoParams: PostSubjectInfoParams) = launchIOCoroutine {
        amendSubjectManagementInfoUseCase(postSubjectInfoParams).fold(
            {
                postError(it)
            },
            {
                _amendMutableSubjectInfo.postValue(it)
            }
        )
    }

    fun checkDuplicateSubjectInfo(checkDuplicateSubjectInfoParams: CheckDuplicateSubjectInfoParams) =
        launchIOCoroutine {
            checkDuplicateSubjectManagementInfoUseCase(checkDuplicateSubjectInfoParams).fold(
                {
                    postError(it)
                },
                {
                    _checkDuplicateMutableSubjectInfo.postValue(it)
                }
            )
        }

    fun populateSubjectList(populateSubjectListParams: PopulateSubjectListParams) =
        launchIOCoroutine {
            populateSubjectListUseCase(populateSubjectListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _populateMutableSubjectList.postValue(result)
                    }
                )
            }
        }

    fun retrieveSubjectList(retrieveSubjectListParams: RetrieveSubjectListParams) =
        launchIOCoroutine {
            retrieveSubjectListUseCase(retrieveSubjectListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _retrieveMutableSubjectList.postValue(result)
                    }
                )
            }
        }

    fun retrieveSubjectDetails(retrieveSubjectDetailsParams: RetrieveSubjectDetailsParams) =
        launchIOCoroutine {
            retrieveSubjectDetailsUseCase(retrieveSubjectDetailsParams).fold(
                {
                    postError(it)
                },
                {
                    _retrieveMutableSubjectDetails.postValue(it)
                }
            )
        }

    fun populateSubjectProgramList(populateSubjectProgramListParams: PopulateSubjectProgramsListParams) =
        launchIOCoroutine {
            populateSubjectProgramsListUseCase(populateSubjectProgramListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _mutablePopulateSubjectProgramList.postValue(result)
                    }
                )
            }
        }

    fun setSubjectProgramStatus(params: SetStatusSubjectProgramParams) = launchIOCoroutine {
        setSubjectProgramStatusUseCase(params).fold(
            {
                postError(it)
            },
            {
                _mutableSetSubjectProgramStatus.postValue(it)
            }
        )
    }
}