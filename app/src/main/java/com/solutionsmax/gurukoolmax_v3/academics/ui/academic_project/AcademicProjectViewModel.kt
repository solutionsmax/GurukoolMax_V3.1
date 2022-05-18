package com.solutionsmax.gurukoolmax_v3.academics.ui.academic_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.*
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.project.RetrieveAcademicProjectItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.academic_project.*
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class AcademicProjectViewModel @Inject constructor(
    private val postAcademicProjectUseCase: PostAcademicProjectUseCase,
    private val amendAcademicProjectUseCase: AmendAcademicProjectUseCase,
    private val amendAcademicProjectGuideInfoUseCase: AmendAcademicProjectGuideInfoUseCase,
    private val setAcademicProjectStatusUseCase: SetAcademicProjectStatusUseCase,
    private val checkDuplicateAcademicProjectUseCase: CheckDuplicateAcademicProjectUseCase,
    private val retrieveAcademicProjectDetailsUseCase: RetrieveAcademicProjectDetailsUseCase,
    private val retrieveAcademicProjectListUseCase: RetrieveAcademicProjectListUseCase,
    private val retrieveAcademicProjectSearchListUseCase: RetrieveAcademicProjectSearchListUseCase
) : BaseViewModel() {

    private val _mutablePostAcademicProject: MutableLiveData<Int> = MutableLiveData()
    val mutablePostAcademicProject: LiveData<Int>
        get() = _mutablePostAcademicProject

    private val _mutableAmendAcademicProject: MutableLiveData<Int> = MutableLiveData()
    val mutableAmendAcademicProject: LiveData<Int>
        get() = _mutableAmendAcademicProject

    private val _mutableAmendAcademicProjectGuide: MutableLiveData<Int> = MutableLiveData()
    val mutableAmendAcademicProjectGuide: LiveData<Int>
        get() = _mutableAmendAcademicProjectGuide

    private val _mutableSetAcademicProjectStatus: MutableLiveData<Int> = MutableLiveData()
    val mutableSetAcademicProjectStatus: LiveData<Int>
        get() = _mutableSetAcademicProjectStatus

    private val _mutableCheckDuplicateAcademicProject: MutableLiveData<Int> = MutableLiveData()
    val mutableCheckDuplicateAcademicProject: LiveData<Int>
        get() = _mutableCheckDuplicateAcademicProject

    private val _mutableRetrieveAcademicProjectDetails: MutableLiveData<List<RetrieveAcademicProjectItem>> =
        MutableLiveData()
    val mutableRetrieveAcademicProjectDetails: LiveData<List<RetrieveAcademicProjectItem>>
        get() = _mutableRetrieveAcademicProjectDetails

    private val _mutableRetrieveAcademicProjectList: MutableLiveData<List<RetrieveAcademicProjectItem>> =
        MutableLiveData()
    val mutableRetrieveAcademicProjectList: LiveData<List<RetrieveAcademicProjectItem>>
        get() = _mutableRetrieveAcademicProjectList

    private val _mutableRetrieveAcademicProjectSearch: MutableLiveData<List<RetrieveAcademicProjectItem>> =
        MutableLiveData()
    val mutableRetrieveAcademicProjectSearch: LiveData<List<RetrieveAcademicProjectItem>>
        get() = _mutableRetrieveAcademicProjectSearch

    fun postAcademicProjectInfo(postAcademicProjectParams: PostAcademicProjectParams) =
        launchIOCoroutine {
            postAcademicProjectUseCase(postAcademicProjectParams).fold(
                {
                    postError(it)
                },
                {
                    _mutablePostAcademicProject.postValue(it)
                }
            )
        }

    fun amendAcademicProjectInfo(postAcademicProjectParams: PostAcademicProjectParams) =
        launchIOCoroutine {
            amendAcademicProjectUseCase(postAcademicProjectParams).fold(
                {
                    postError(it)
                },
                {
                    _mutableAmendAcademicProject.postValue(it)
                }
            )
        }

    fun amendAcademicProjectGuideInfo(academicProjectGuideParams: AcademicProjectGuideParams) =
        launchIOCoroutine {
            amendAcademicProjectGuideInfoUseCase(academicProjectGuideParams).fold(
                {
                    postError(it)
                },
                {
                    _mutableAmendAcademicProjectGuide.postValue(it)
                }
            )
        }

    fun setAcademicProjectStatus(academicProjectSetStatusParams: AcademicProjectSetStatusParams) =
        launchIOCoroutine {
            setAcademicProjectStatusUseCase(academicProjectSetStatusParams).fold(
                {
                    postError(it)
                },
                {
                    _mutableSetAcademicProjectStatus.postValue(it)
                }
            )
        }

    fun checkDuplicateAcademicProject(academicProjectCheckDuplicateParams: AcademicProjectCheckDuplicateParams) =
        launchIOCoroutine {
            checkDuplicateAcademicProjectUseCase(academicProjectCheckDuplicateParams).fold(
                {
                    postError(it)
                },
                {
                    _mutableCheckDuplicateAcademicProject.postValue(it)
                }
            )
        }

    fun retrieveAcademicProjectDetails(academicProjectRetrieveDetailsParams: AcademicProjectRetrieveDetailsParams) =
        launchIOCoroutine {
            retrieveAcademicProjectDetailsUseCase(academicProjectRetrieveDetailsParams).fold(
                {
                    postError(it)
                },
                {
                    _mutableRetrieveAcademicProjectDetails.postValue(it)
                }
            )
        }

    fun retrieveAcademicProjectList(academicProjectRetrieveListParams: AcademicProjectRetrieveListParams) =
        launchIOCoroutine {
            retrieveAcademicProjectListUseCase(academicProjectRetrieveListParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _mutableRetrieveAcademicProjectList.postValue(result)
                    }
                )
            }
        }

    fun retrieveAcademicProjectSearch(academicProjectSearchParams: AcademicProjectSearchParams) =
        launchIOCoroutine {
            retrieveAcademicProjectSearchListUseCase(academicProjectSearchParams).collect {
                it.fold(
                    { error ->
                        postError(error)
                    },
                    { result ->
                        _mutableRetrieveAcademicProjectSearch.postValue(result)
                    }
                )
            }
        }
}