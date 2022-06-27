package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.course_syllabus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.PopulateCurriculumSessionTopicList
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.curriculum.RetrieveCurriculumInfoItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.curriculum.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CurriculumInfoViewModel @Inject constructor(
    private val postCurriculumInfoUseCase: PostCurriculumInfoUseCase,
    private val amendCurriculumInfoUseCase: AmendCurriculumInfoUseCase,
    private val checkDuplicateCurriculumInfoUseCase: CheckDuplicateCurriculumInfoUseCase,
    private val populateSessionTopicListUseCase: PopulateSessionTopicListUseCase,
    private val populateCurriculumListByClassReferenceUseCase: PopulateCurriculumListByClassReferenceUseCase,
    private val retrieveCurriculumDetailsUseCase: RetrieveCurriculumDetailsUseCase,
    private val retrieveCurriculumListUseCase: RetrieveCurriculumListUseCase,
    private val setCurriculumStatusUseCase:SetCurriculumStatusUseCase
) : BaseViewModel() {

    private val _postMutableCurriculumInfo: MutableLiveData<Int> = MutableLiveData()
    val postMutableCurriculumInfo: LiveData<Int>
        get() = _postMutableCurriculumInfo

    private val _amendMutableCurriculumInfo: MutableLiveData<Int> = MutableLiveData()
    val amendMutableCurriculumInfo: LiveData<Int>
        get() = _amendMutableCurriculumInfo

    private val _checkDuplicateMutableCurriculumInfo: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateMutableCurriculumInfo: LiveData<Int>
        get() = _checkDuplicateMutableCurriculumInfo

    private val _populateMutableSessionTopicList: MutableLiveData<List<PopulateCurriculumSessionTopicList>> =
        MutableLiveData()
    val populateMutableSessionTopicList: LiveData<List<PopulateCurriculumSessionTopicList>>
        get() = _populateMutableSessionTopicList

    private val _populateMutableCurriculumListByClassReference: MutableLiveData<List<PopulateCurriculumSessionTopicList>> =
        MutableLiveData()
    val populateMutableCurriculumListByClassReference: LiveData<List<PopulateCurriculumSessionTopicList>>
        get() = _populateMutableCurriculumListByClassReference

    private val _retrieveMutableCurriculumDetails: MutableLiveData<List<RetrieveCurriculumInfoItems>> =
        MutableLiveData()
    val retrieveMutableCurriculumDetails: LiveData<List<RetrieveCurriculumInfoItems>>
        get() = _retrieveMutableCurriculumDetails

    private val _retrieveMutableCurriculumList: MutableLiveData<List<RetrieveCurriculumInfoItems>> =
        MutableLiveData()
    val retrieveMutableCurriculumList: LiveData<List<RetrieveCurriculumInfoItems>>
        get() = _retrieveMutableCurriculumList

    private val _mutableSetCurriculumStatus:MutableLiveData<Int> = MutableLiveData()
    val mutableSetCurriculumStatus:LiveData<Int>
        get() = _mutableSetCurriculumStatus

    fun postCurriculumInfo(postCurriculumInfoParams: PostCurriculumInfoParams) = launchIOCoroutine {
        postCurriculumInfoUseCase(postCurriculumInfoParams).fold(
            {
                postError(it)
            },
            {
                _postMutableCurriculumInfo.postValue(it)
            }
        )
    }

    fun amendCurriculumInfo(postCurriculumInfoParams: PostCurriculumInfoParams) =
        launchIOCoroutine {
            amendCurriculumInfoUseCase(postCurriculumInfoParams).fold(
                {
                    postError(it)
                },
                {
                    _amendMutableCurriculumInfo.postValue(it)
                }
            )
        }

    fun checkDuplicateCurriculumInfo(checkDuplicateCurriculumInfoParams: CheckDuplicateCurriculumInfoParams) =
        launchIOCoroutine {
            checkDuplicateCurriculumInfoUseCase(checkDuplicateCurriculumInfoParams).fold(
                {
                    postError(it)
                },
                {
                    _checkDuplicateMutableCurriculumInfo.postValue(it)
                }
            )
        }

    fun populateSessionTopicList(populateCurriculumSessionTopicListParams: PopulateCurriculumSessionTopicListParams) =
        launchIOCoroutine {
            populateSessionTopicListUseCase(populateCurriculumSessionTopicListParams).collect {
                it.fold(
                    { error->
                        postError(error)
                    },
                    { result->
                        _populateMutableSessionTopicList.postValue(result)
                    }
                )
            }
        }

    fun populateCurriculumListByClassReference(populateCurriculumSessionTopicListParams: PopulateCurriculumSessionTopicListParams) =
        launchIOCoroutine {
            populateCurriculumListByClassReferenceUseCase(populateCurriculumSessionTopicListParams).collect {
                it.fold(
                    { error->
                        postError(error)
                    },
                    { result->
                        _populateMutableCurriculumListByClassReference.postValue(result)
                    }
                )
            }
        }

    fun retrieveCurriculumDetails(retrieveCurriculumDetailsParams: RetrieveCurriculumDetailsParams) =
        launchIOCoroutine {
            retrieveCurriculumDetailsUseCase(retrieveCurriculumDetailsParams).fold(
                {
                    postError(it)
                },
                {
                    _retrieveMutableCurriculumDetails.postValue(it)
                }
            )
        }

    fun retrieveCurriculumListInfo(retrieveCurriculumListParams: RetrieveCurriculumListParams) =
        launchIOCoroutine {
            retrieveCurriculumListUseCase(retrieveCurriculumListParams).collect {
                it.fold(
                    {   error->
                        postError(error)
                    },
                    { result->
                        _retrieveMutableCurriculumList.postValue(result)
                    }
                )
            }
        }

    fun setCurriculumStatus(setCurriculumStatusParams: SetCurriculumStatusParams) = launchIOCoroutine {
        setCurriculumStatusUseCase(setCurriculumStatusParams).fold(
            {
                postError(it)
            },
            {
                _mutableSetCurriculumStatus.postValue(it)
            }
        )
    }
}