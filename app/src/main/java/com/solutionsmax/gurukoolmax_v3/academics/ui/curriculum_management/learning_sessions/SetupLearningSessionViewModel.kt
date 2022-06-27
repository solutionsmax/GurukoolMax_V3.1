package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.learning_session.RetrieveLearningSessionItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.learning_session.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SetupLearningSessionViewModel @Inject constructor(
    private val postLearningSessionInfoUseCase: PostLearningSessionInfoUseCase,
    private val amendLearningSessionInfoUseCase: AmendLearningSessionInfoUseCase,
    private val amendLearningSessionProgressUseCase: AmendLearningSessionProgressUseCase,
    private val checkDuplicateLearningSessionUseCase: CheckDuplicateLearningSessionUseCase,
    private val retrieveCurriculumTopicListByFacultyUseCase: RetrieveCurriculumTopicListByFacultyUseCase,
    private val retrieveLearningSessionDetailsUseCase: RetrieveLearningSessionDetailsUseCase,
    private val retrieveLearningSessionListUseCase: RetrieveLearningSessionListUseCase,
    private val retrieveLearningSessionListByFacultyUseCase: RetrieveLearningSessionListByFacultyUseCase,
    private val setLearningSessionStatusUseCase: SetLearningSessionStatusUseCase
) : BaseViewModel() {

    private val _postLearningSessionMutableData: MutableLiveData<Int> = MutableLiveData()
    val postLearningSessionMutableData: LiveData<Int>
        get() = _postLearningSessionMutableData

    private val _amendLearningSessionMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendLearningSessionMutableData: LiveData<Int>
        get() = _amendLearningSessionMutableData

    private val _amendLearningSessionProgressMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendLearningSessionProgressMutableData: LiveData<Int>
        get() = _amendLearningSessionProgressMutableData

    private val _checkDuplicateLearningSessionProgressMutableData: MutableLiveData<Int> =
        MutableLiveData()
    val checkDuplicateLearningSessionProgressMutableData: LiveData<Int>
        get() = _checkDuplicateLearningSessionProgressMutableData

    private val _retrieveCurriculumTopicListByFacultyMutableData: MutableLiveData<List<RetrieveLearningSessionItem>> =
        MutableLiveData()
    val retrieveCurriculumTopicListByFacultyMutableData: LiveData<List<RetrieveLearningSessionItem>>
        get() = _retrieveCurriculumTopicListByFacultyMutableData

    private val _retrieveLearningSessionDetailsMutableData: MutableLiveData<List<RetrieveLearningSessionItem>> =
        MutableLiveData()
    val retrieveLearningSessionDetailsMutableData: LiveData<List<RetrieveLearningSessionItem>>
        get() = _retrieveLearningSessionDetailsMutableData

    private val _retrieveLearningSessionListMutableData: MutableLiveData<List<RetrieveLearningSessionItem>> =
        MutableLiveData()
    val retrieveLearningSessionListMutableData: LiveData<List<RetrieveLearningSessionItem>>
        get() = _retrieveLearningSessionListMutableData

    private val _retrieveLearningSessionListByFacultyMutableData: MutableLiveData<List<RetrieveLearningSessionItem>> =
        MutableLiveData()
    val retrieveLearningSessionListByFacultyMutableData: LiveData<List<RetrieveLearningSessionItem>>
        get() = _retrieveLearningSessionListByFacultyMutableData

    private val _mutableSetLearningSessionStatus: MutableLiveData<Int> = MutableLiveData()
    val mutableSetLearningSessionStatus: LiveData<Int>
        get() = _mutableSetLearningSessionStatus

    fun postLearningSessionInfo(postLearningSessionParams: PostLearningSessionParams) =
        launchIOCoroutine {
            postLearningSessionInfoUseCase(postLearningSessionParams).fold(
                {
                    postError(it)
                },
                {
                    _postLearningSessionMutableData.postValue(it)
                }
            )
        }

    fun amendLearningSessionInfo(postLearningSessionParams: PostLearningSessionParams) =
        launchIOCoroutine {
            amendLearningSessionInfoUseCase(postLearningSessionParams).fold(
                {
                    postError(it)
                },
                {
                    _amendLearningSessionMutableData.postValue(it)
                }
            )
        }

    fun amendLearningSessionProgress(amendLearningSessionProgressParams: AmendLearningSessionProgressParams) =
        launchIOCoroutine {
            amendLearningSessionProgressUseCase(amendLearningSessionProgressParams).fold(
                {
                    postError(it)
                },
                {
                    _amendLearningSessionProgressMutableData.postValue(it)
                }
            )
        }

    fun checkDuplicateLearningSession(checkDuplicateLearningSessionProgressParams: CheckDuplicateLearningSessionProgressParams) =
        launchIOCoroutine {
            checkDuplicateLearningSessionUseCase(checkDuplicateLearningSessionProgressParams).fold(
                {
                    postError(it)
                },
                {
                    _checkDuplicateLearningSessionProgressMutableData.postValue(it)
                }
            )
        }

    fun retrieveCurriculumTopicListByFacultyInfo(retrieveCurriculumTopicListByFacultyParams: RetrieveCurriculumTopicListByFacultyParams) =
        launchIOCoroutine {
            retrieveCurriculumTopicListByFacultyUseCase(retrieveCurriculumTopicListByFacultyParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _retrieveCurriculumTopicListByFacultyMutableData.postValue(result)
                    }
                )
            }
        }

    fun retrieveLearningSessionDetailsInfo(retrieveLearningSessionDetailsParams: RetrieveLearningSessionDetailsParams) =
        launchIOCoroutine {
            retrieveLearningSessionDetailsUseCase(retrieveLearningSessionDetailsParams).fold(
                {
                    postError(it)
                },
                {
                    _retrieveLearningSessionDetailsMutableData.postValue(it)
                }
            )
        }

    fun retrieveLearningSessionListInfo(retrieveLearningSessionListParams: RetrieveLearningSessionListParams) =
        launchIOCoroutine {
            retrieveLearningSessionListUseCase(retrieveLearningSessionListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _retrieveLearningSessionListMutableData.postValue(result)
                    }
                )
            }
        }

    fun retrieveLearningSessionListByFacultyInfo(retrieveLearningSessionListParams: RetrieveLearningSessionListParams) =
        launchIOCoroutine {
            retrieveLearningSessionListByFacultyUseCase(retrieveLearningSessionListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _retrieveLearningSessionListByFacultyMutableData.postValue(result)
                    }
                )
            }
        }

    fun setLearningSessionStatus(setLearningSessionStatusParams: SetLearningSessionStatusParams) =
        launchIOCoroutine {
            setLearningSessionStatusUseCase(setLearningSessionStatusParams).fold(
                {
                    postError(it)
                },
                {
                    _mutableSetLearningSessionStatus.postValue(it)
                }
            )
        }

}