package com.solutionsmax.gurukoolmax_v3.academics.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateClassParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateSemesterClassParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.common.PopulateClassUseCase
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.common.PopulateSemesterClassUseCase
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class AcademicsViewModel @Inject constructor(
    private val populateSemesterClassUseCase: PopulateSemesterClassUseCase,
    private val populateClassUseCase: PopulateClassUseCase
) : BaseViewModel() {

    private val _mutablePopulateSemesterClass: MutableLiveData<MutableList<PopulateClassItems>> =
        MutableLiveData()
    val mutablePopulateSemesterClass: LiveData<MutableList<PopulateClassItems>>
        get() = _mutablePopulateSemesterClass

    private val _mutablePopulateClass:MutableLiveData<MutableList<PopulateClassItems>> = MutableLiveData()
    val mutablePopulateClass:LiveData<MutableList<PopulateClassItems>>
        get() = _mutablePopulateClass


    fun populateClassSemester(populateClassParams: PopulateSemesterClassParams) = launchIOCoroutine {
        populateSemesterClassUseCase(populateClassParams).collect {
            it.fold(
                { error ->
                    postError(error)
                },
                { result ->
                    _mutablePopulateSemesterClass.postValue(result)
                }
            )
        }
    }

    fun populateClass(populateClassParams: PopulateClassParams) = launchIOCoroutine {
        populateClassUseCase(populateClassParams).collect {
            it.fold(
                { error ->
                    postError(error)
                },
                { result ->
                    _mutablePopulateClass.postValue(result)
                }
            )
        }
    }
}