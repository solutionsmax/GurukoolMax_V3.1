package com.solutionsmax.gurukoolmax_v3.academics.ui.map_subject_to_faculty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.map_subject_to_faculty.PopulateMapSubjectToFacultyItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateMapSubjectToFacultyParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.map_subject_to_faculty.PopulateMapSubjectToFacultyUseCase
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import javax.inject.Inject

class MapSubjectToFacultyViewModel @Inject constructor(
    private val populateMapSubjectToFacultyUseCase: PopulateMapSubjectToFacultyUseCase
) : BaseViewModel() {

    private val _populateMapSubjectToFacultyMutableData: MutableLiveData<List<PopulateMapSubjectToFacultyItem>> =
        MutableLiveData()
    val populateMapSubjectToFacultyMutableData: LiveData<List<PopulateMapSubjectToFacultyItem>>
        get() = _populateMapSubjectToFacultyMutableData

    fun populateMapSubjectToFaculty(populateMapSubjectToFacultyParams: PopulateMapSubjectToFacultyParams) =
        launchIOCoroutine {
            populateMapSubjectToFacultyUseCase(populateMapSubjectToFacultyParams).fold(
                {
                    postError(it)
                },
                {
                    _populateMapSubjectToFacultyMutableData.postValue(it)
                }
            )
        }
}