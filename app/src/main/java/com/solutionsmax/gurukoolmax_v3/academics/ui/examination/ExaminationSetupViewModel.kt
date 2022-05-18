package com.solutionsmax.gurukoolmax_v3.academics.ui.examination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.exam_setup.RetrieveExamSetupDetails
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.CheckDuplicateExamParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PostExamSetupParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveExamSetupDetailsParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveExamSetupListParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.examination.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ExaminationSetupViewModel @Inject constructor(
    private val postExamSetupInfoUseCase: PostExamSetupInfoUseCase,
    private val amendExamSetupInfoUseCase: AmendExamSetupInfoUseCase,
    private val checkDuplicateExamSetupUseCase: CheckDuplicateExamSetupUseCase,
    private val retrieveExamSetupDetailUseCase: RetrieveExamSetupDetailUseCase,
    private val retrieveExamSetupListUseCase: RetrieveExamSetupListUseCase
) : BaseViewModel() {

    private val _postExaminationSetupMutableData: MutableLiveData<Int> = MutableLiveData()
    val postExaminationSetupMutableData: LiveData<Int>
        get() = _postExaminationSetupMutableData

    private val _amendExaminationSetupMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendExaminationSetupMutableData: LiveData<Int>
        get() = _amendExaminationSetupMutableData

    private val _checkDuplicateExaminationSetupMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkDuplicateExaminationSetupMutableData: LiveData<Int>
        get() = _checkDuplicateExaminationSetupMutableData

    private val _retrieveExamSetupDetailsMutableData: MutableLiveData<List<RetrieveExamSetupDetails>> =
        MutableLiveData()
    val retrieveExamSetupDetailsMutableData: LiveData<List<RetrieveExamSetupDetails>>
        get() = _retrieveExamSetupDetailsMutableData

    private val _retrieveExamSetupListMutableData: MutableLiveData<List<RetrieveExamSetupDetails>> =
        MutableLiveData()
    val retrieveExamSetupListMutableData: LiveData<List<RetrieveExamSetupDetails>>
        get() = _retrieveExamSetupListMutableData

    fun postExamSetupInfo(postExamSetupParams: PostExamSetupParams) = launchIOCoroutine {
        postExamSetupInfoUseCase(postExamSetupParams).fold(
            {
                postError(it)
            },
            {
                _postExaminationSetupMutableData.postValue(it)
            }
        )
    }

    fun amendExamSetupInfo(postExamSetupParams: PostExamSetupParams) = launchIOCoroutine {
        amendExamSetupInfoUseCase(postExamSetupParams).fold(
            {
                postError(it)
            },
            {
                _amendExaminationSetupMutableData.postValue(it)
            }
        )
    }

    fun checkDuplicateExamSetupInfo(checkDuplicateExamParams: CheckDuplicateExamParams) =
        launchIOCoroutine {
            checkDuplicateExamSetupUseCase(checkDuplicateExamParams).fold(
                {
                    postError(it)
                },
                {
                    _checkDuplicateExaminationSetupMutableData.postValue(it)
                }
            )
        }

    fun retrieveExamSetupDetails(retrieveExamSetupDetailsParams: RetrieveExamSetupDetailsParams) =
        launchIOCoroutine {
            retrieveExamSetupDetailUseCase(retrieveExamSetupDetailsParams).fold(
                {
                    postError(it)
                },
                {
                    _retrieveExamSetupDetailsMutableData.postValue(it)
                }
            )
        }

    fun retrieveExamSetupList(retrieveExamSetupListParams: RetrieveExamSetupListParams) =
        launchIOCoroutine {
            retrieveExamSetupListUseCase(retrieveExamSetupListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _retrieveExamSetupListMutableData.postValue(result)
                    }
                )
            }

        }

}