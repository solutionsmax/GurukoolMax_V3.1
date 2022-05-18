package com.solutionsmax.gurukoolmax_v3.academics.ui.km

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.RetrieveKMInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.km.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class KmViewModel @Inject constructor(
    private val postKMInfoUseCase: PostKMInfoUseCase,
    private val amendKMInfoUseCase: AmendKMInfoUseCase,
    private val postKmFileNameUseCase: PostKmFileNameUseCase,
    private val removeKmInfoUseCase: RemoveKmInfoUseCase,
    private val retrieveKmAdvanceSearchUseCase: RetrieveKmAdvanceSearchUseCase,
    private val retrieveKmDetailsUseCase: RetrieveKmDetailsUseCase,
    private val retrieveKmListUseCase: RetrieveKmListUseCase,
    private val setStatusKMInfoUseCase: SetStatusKMInfoUseCase
) : BaseViewModel() {

    private val _mutablePostKmInfo: MutableLiveData<Int> = MutableLiveData()
    val mutablePostKmInfo: LiveData<Int>
        get() = _mutablePostKmInfo

    private val _mutableAmendKmInfo: MutableLiveData<Int> = MutableLiveData()
    val mutableAmendKmInfo: LiveData<Int>
        get() = _mutableAmendKmInfo

    private val _mutablePostKmFileNameInfo: MutableLiveData<Int> = MutableLiveData()
    val mutablePostKmFileNameInfo: LiveData<Int>
        get() = _mutablePostKmFileNameInfo

    private val _mutableRemoveKmInfo: MutableLiveData<Int> = MutableLiveData()
    val mutableRemoveKmInfo: LiveData<Int>
        get() = _mutableRemoveKmInfo

    private val _mutableKmAdvanceSearch: MutableLiveData<List<RetrieveKMInfo>> = MutableLiveData()
    val mutableKmAdvanceSearch: LiveData<List<RetrieveKMInfo>>
        get() = _mutableKmAdvanceSearch

    private val _mutableKmDetails: MutableLiveData<List<RetrieveKMInfo>> = MutableLiveData()
    val mutableKmDetails: LiveData<List<RetrieveKMInfo>>
        get() = _mutableKmDetails

    private val _mutableKmList: MutableLiveData<List<RetrieveKMInfo>> = MutableLiveData()
    val mutableKmList: LiveData<List<RetrieveKMInfo>>
        get() = _mutableKmList

    private val _mutableKmSetStatus: MutableLiveData<Int> = MutableLiveData()
    val mutableKmSetStatus: LiveData<Int>
        get() = _mutableKmSetStatus

    fun postKmInfo(kmPostParams: KmPostParams) = launchIOCoroutine {
        postKMInfoUseCase(kmPostParams).fold(
            {
                postError(it)
            },
            {
                _mutablePostKmInfo.postValue(it)
            }
        )
    }

    fun amendKmInfo(kmPostParams: KmPostParams) = launchIOCoroutine {
        amendKMInfoUseCase(kmPostParams).fold(
            {
                postError(it)
            },
            {
                _mutableAmendKmInfo.postValue(it)
            }
        )
    }

    fun postKmFileName(kmPostFileNameParams: KmPostFileNameParams) = launchIOCoroutine {
        postKmFileNameUseCase(kmPostFileNameParams).fold(
            {
                postError(it)
            },
            {
                _mutablePostKmFileNameInfo.postValue(it)
            }
        )
    }

    fun removeKmInfo(kmRemoveInfoParams: KmRemoveInfoParams) = launchIOCoroutine {
        removeKmInfoUseCase(kmRemoveInfoParams).fold(
            {
                postError(it)
            },
            {
                _mutableRemoveKmInfo.postValue(it)
            }
        )
    }

    fun retrieveKmAdvanceSearch(kmRetrieveAdvanceSearchParams: KmRetrieveAdvanceSearchParams) =
        launchIOCoroutine {
            retrieveKmAdvanceSearchUseCase(kmRetrieveAdvanceSearchParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _mutableKmAdvanceSearch.postValue(result)
                    }
                )
            }
        }

    fun retrieveKmDetails(kmRetrieveDetailsParams: KmRetrieveDetailsParams) = launchIOCoroutine {
        retrieveKmDetailsUseCase(kmRetrieveDetailsParams).fold(
            {
                postError(it)
            },
            {
                _mutableKmDetails.postValue(it)
            }
        )
    }

    fun retrieveKmList(kmRetrieveListParams: KmRetrieveListParams) = launchIOCoroutine {
        retrieveKmListUseCase(kmRetrieveListParams).collect {
            it.fold(
                { error ->
                    postError(error)
                },
                { result ->
                    _mutableKmList.postValue(result)
                }
            )
        }
    }

    fun setKmStatus(kmSetStatusParams: KmSetStatusParams) = launchIOCoroutine {
        setStatusKMInfoUseCase(kmSetStatusParams).fold(
            {
                postError(it)
            },
            {
                _mutableKmSetStatus.postValue(it)
            }
        )
    }
}