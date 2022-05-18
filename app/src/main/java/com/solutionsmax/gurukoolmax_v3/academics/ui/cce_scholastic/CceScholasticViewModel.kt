package com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.RetrieveCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CceScholasticViewModel @Inject constructor(
    private val postCceScholasticInfoUseCase: PostCceScholasticInfoUseCase,
    private val amendCceScholasticInfoUseCase: AmendCceScholasticInfoUseCase,
    private val checkDuplicateScholasticGradingAllUseCase: CheckDuplicateScholasticGradingAllUseCase,
    private val checkDuplicateScholasticGradingByMaxRangeUseCase: CheckDuplicateScholasticGradingByMaxRangeUseCase,
    private val checkDuplicateScholasticGradingByMinRangeUseCase: CheckDuplicateScholasticGradingByMinRangeUseCase,
    private val checkDuplicateScholasticGradingPointUseCase: CheckDuplicateScholasticGradingPointUseCase,
    private val checkDuplicateScholasticGradingUseCase: CheckDuplicateScholasticGradingUseCase,
    private val removeScholasticGradingGradeUseCase: RemoveScholasticGradingGradeUseCase,
    private val retrieveDetailsScholasticGradingUseCase: RetrieveDetailsScholasticGradingUseCase,
    private val retrieveListScholasticGradingUseCase: RetrieveListScholasticGradingUseCase,
    private val retrieveGradeByListScholasticGradingUseCase: RetrieveGradeByListScholasticGradingUseCase,
    private val setStatusScholasticGradingUseCase: SetStatusScholasticGradingUseCase
) : BaseViewModel() {

    private val _mutablePostCceScholasticMutableDate: MutableLiveData<Int> = MutableLiveData()
    val mutablePostCceScholasticMutableDate: LiveData<Int>
        get() = _mutablePostCceScholasticMutableDate

    private val _mutableAmendCceScholasticMutableDate: MutableLiveData<Int> = MutableLiveData()
    val mutableAmendCceScholasticMutableDate: LiveData<Int>
        get() = _mutableAmendCceScholasticMutableDate

    private val _mutableCheckDuplicateScholasticGradingAll: MutableLiveData<Int> = MutableLiveData()
    val mutableCheckDuplicateScholasticGradingAll: LiveData<Int>
        get() = _mutableCheckDuplicateScholasticGradingAll

    private val _mutableCheckDuplicateScholasticByMaxRange: MutableLiveData<Int> = MutableLiveData()
    val mutableCheckDuplicateScholasticByMaxRange: LiveData<Int>
        get() = _mutableCheckDuplicateScholasticByMaxRange

    private val _mutableCheckDuplicateScholasticByMinRange: MutableLiveData<Int> = MutableLiveData()
    val mutableCheckDuplicateScholasticByMinRange: LiveData<Int>
        get() = _mutableCheckDuplicateScholasticByMinRange

    private val _mutableCheckDuplicateScholasticGradingPoint: MutableLiveData<Int> =
        MutableLiveData()
    val mutableCheckDuplicateScholasticGradingPoint: LiveData<Int>
        get() = _mutableCheckDuplicateScholasticGradingPoint

    private val _mutableCheckDuplicateScholasticGrading: MutableLiveData<Int> = MutableLiveData()
    val mutableCheckDuplicateScholasticGrading: LiveData<Int>
        get() = _mutableCheckDuplicateScholasticGrading

    private val _mutableRemoveScholasticGradingGrade: MutableLiveData<Int> = MutableLiveData()
    val mutableRemoveScholasticGradingGrade: LiveData<Int>
        get() = _mutableRemoveScholasticGradingGrade

    private val _mutableRetrieveDetailsScholasticGrading: MutableLiveData<List<RetrieveCceScholasticItem>> =
        MutableLiveData()
    val mutableRetrieveDetailsScholasticGrading: LiveData<List<RetrieveCceScholasticItem>>
        get() = _mutableRetrieveDetailsScholasticGrading

    private val _mutableRetrieveListScholasticGrading: MutableLiveData<List<RetrieveCceScholasticItem>> =
        MutableLiveData()
    val mutableRetrieveListScholasticGrading: LiveData<List<RetrieveCceScholasticItem>>
        get() = _mutableRetrieveListScholasticGrading

    private val _mutableRetrieveGradeByListScholasticGrading: MutableLiveData<List<RetrieveCceScholasticItem>> =
        MutableLiveData()
    val mutableRetrieveGradeByListScholasticGrading: LiveData<List<RetrieveCceScholasticItem>>
        get() = _mutableRetrieveGradeByListScholasticGrading

    private val _mutableSetStatusScholasticGrading: MutableLiveData<Int> = MutableLiveData()
    val mutableSetStatusScholasticGrading: LiveData<Int>
        get() = _mutableSetStatusScholasticGrading

    fun postCceScholasticInfo(postCceScholasticParams: PostCceScholasticParams) =
        launchIOCoroutine {
            postCceScholasticInfoUseCase(postCceScholasticParams).fold(
                {
                    postError(it)
                },
                {
                    _mutablePostCceScholasticMutableDate.postValue(it)
                }
            )
        }

    fun amendCceScholasticInfo(postCceScholasticParams: PostCceScholasticParams) =
        launchIOCoroutine {
            amendCceScholasticInfoUseCase(postCceScholasticParams).fold(
                {
                    postError(it)
                },
                {
                    _mutableAmendCceScholasticMutableDate.postValue(it)
                }
            )
        }

    fun checkDuplicateScholasticGradingAllInfo(checkDuplicateScholasticGradingAll: CheckDuplicateScholasticGradingAll) =
        launchIOCoroutine {
            checkDuplicateScholasticGradingAllUseCase(checkDuplicateScholasticGradingAll).fold(
                {
                    postError(it)
                },
                {
                    _mutableCheckDuplicateScholasticGradingAll.postValue(it)
                }
            )
        }

    fun checkDuplicateScholasticByMaxRange(checkDuplicateScholasticMaxMinGrading: CheckDuplicateScholasticMaxMinGrading) =
        launchIOCoroutine {
            checkDuplicateScholasticGradingByMaxRangeUseCase(checkDuplicateScholasticMaxMinGrading).fold(
                {
                    postError(it)
                },
                {
                    _mutableCheckDuplicateScholasticByMaxRange.postValue(it)
                }
            )
        }

    fun checkDuplicateScholasticByMinRange(checkDuplicateScholasticMaxMinGrading: CheckDuplicateScholasticMaxMinGrading) =
        launchIOCoroutine {
            checkDuplicateScholasticGradingByMinRangeUseCase(checkDuplicateScholasticMaxMinGrading).fold(
                {
                    postError(it)
                },
                {
                    _mutableCheckDuplicateScholasticByMinRange.postValue(it)
                }
            )
        }

    fun checkDuplicateScholasticGradingPoint(checkDuplicateScholasticGradPointGrading: CheckDuplicateScholasticGradPointGrading) =
        launchIOCoroutine {
            checkDuplicateScholasticGradingPointUseCase(checkDuplicateScholasticGradPointGrading).fold(
                {
                    postError(it)
                },
                {
                    _mutableCheckDuplicateScholasticGradingPoint.postValue(it)
                }
            )
        }

    fun checkDuplicateScholasticGrading(checkDuplicateScholasticGrading: CheckDuplicateScholasticGrading) =
        launchIOCoroutine {
            checkDuplicateScholasticGradingUseCase(checkDuplicateScholasticGrading).fold(
                {
                    postError(it)
                },
                {
                    _mutableCheckDuplicateScholasticGrading.postValue(it)
                }
            )
        }

    fun removeScholasticGradingGrade(idScholasticGrading: IdScholasticGrading) = launchIOCoroutine {
        removeScholasticGradingGradeUseCase(idScholasticGrading).fold(
            {
                postError(it)
            },
            {
                _mutableRemoveScholasticGradingGrade.postValue(it)
            }
        )
    }

    fun retrieveDetailsScholasticGrading(idScholasticGrading: IdScholasticGrading) =
        launchIOCoroutine {
            retrieveDetailsScholasticGradingUseCase(idScholasticGrading).fold(
                {
                    postError(it)
                },
                {
                    _mutableRetrieveDetailsScholasticGrading.postValue(it)
                }
            )
        }

    fun retrieveListScholasticGrading(retrieveListScholasticGrading: RetrieveListScholasticGrading) =
        launchIOCoroutine {
            retrieveListScholasticGradingUseCase(retrieveListScholasticGrading).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _mutableRetrieveListScholasticGrading.postValue(result)
                    }
                )
            }
        }

    fun retrieveGradeByListScholasticGrading(retrieveGradeByListScholasticGrading: RetrieveGradeByListScholasticGrading) =
        launchIOCoroutine {
            retrieveGradeByListScholasticGradingUseCase(retrieveGradeByListScholasticGrading).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _mutableRetrieveGradeByListScholasticGrading.postValue(result)
                    }
                )
            }
        }

    fun setStatusScholasticGrading(idScholasticGrading: IdScholasticGrading) = launchIOCoroutine {
        setStatusScholasticGradingUseCase(idScholasticGrading).fold(
            {
                postError(it)
            },
            {
                _mutableSetStatusScholasticGrading.postValue(it)
            }
        )
    }

}