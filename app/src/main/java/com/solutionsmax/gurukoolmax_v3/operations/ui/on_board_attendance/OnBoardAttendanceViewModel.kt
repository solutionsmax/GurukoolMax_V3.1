package com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseViewModel
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.on_board_attendance.OnBoardAttendancePostItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance.*
import com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.on_board_attendance.*
import javax.inject.Inject

class OnBoardAttendanceViewModel @Inject constructor(
    private val checkValidAdmissionNumberUseCase: CheckValidAdmissionNumberUseCase,
    private val checkStudentAttendanceUseCase: CheckStudentAttendanceUseCase,
    private val checkDuplicateStudentAttendanceUseCase: CheckDuplicateStudentAttendanceUseCase,
    private val amendFleetStudentAttendanceInfoUseCase: AmendFleetStudentAttendanceInfoUseCase,
    private val postFleetStudentAttendanceManuallyUseCase: PostFleetStudentAttendanceManuallyUseCase,
    private val postFleetStudentAttendanceInfoUseCase: PostFleetStudentAttendanceInfoUseCase
) : BaseViewModel() {

    private val _checkValidAdmissionNumberMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkValidAdmissionNumberMutableData: LiveData<Int>
        get() = _checkValidAdmissionNumberMutableData

    private val _checkStudentAttendanceMutableData: MutableLiveData<Int> = MutableLiveData()
    val checkStudentAttendanceMutableData: LiveData<Int>
        get() = _checkStudentAttendanceMutableData

    private val _checkDuplicateStudentAttendanceMutableData: MutableLiveData<Int> =
        MutableLiveData()
    val checkDuplicateStudentAttendanceMutableData: LiveData<Int>
        get() = _checkDuplicateStudentAttendanceMutableData

    private val _amendFleetStudentAttendanceMutableData: MutableLiveData<Int> = MutableLiveData()
    val amendFleetStudentAttendanceMutableData: LiveData<Int>
        get() = _amendFleetStudentAttendanceMutableData

    private val _postFleetStudentAttendanceMutableData: MutableLiveData<Int> = MutableLiveData()
    val postFleetStudentAttendanceMutableData: LiveData<Int>
        get() = _postFleetStudentAttendanceMutableData

    private val _postFleetStudentAttendanceManuallyMutableData: MutableLiveData<Int> =
        MutableLiveData()
    val postFleetStudentAttendanceManuallyMutableData: LiveData<Int>
        get() = _postFleetStudentAttendanceManuallyMutableData

    fun checkValidAdmissionNumber(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        sAdmissionNum: String
    ) = launchIOCoroutine {
        checkValidAdmissionNumberUseCase(
            OnBoardAttendanceParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                sAdmissionNum = sAdmissionNum
            )
        ).fold(
            {
                postError(it)
            },
            {
                _checkValidAdmissionNumberMutableData.postValue(it)
            }
        )
    }

    fun checkStudentAttendance(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStudentID: Int,
        sAdmissionNum: String,
        iRouteID: Int,
        dDateOfTravel: String
    ) = launchIOCoroutine {
        checkStudentAttendanceUseCase(
            OnBoardCheckAttendanceParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iStudentID = iStudentID,
                sAdmissionNum = sAdmissionNum,
                iRouteID = iRouteID,
                dDateOfTravel = dDateOfTravel
            )
        ).fold(
            {
                postError(it)
            },
            {
                _checkStudentAttendanceMutableData.postValue(it)
            }
        )
    }

    fun checkDuplicateStudentAttendance(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStudentID: Int,
        iRouteID: Int,
        dDateOfTravel: String
    ) = launchIOCoroutine {
        checkDuplicateStudentAttendanceUseCase(
            OnBoardCheckDuplicateAttendanceParams(
                url = url,
                sAuthorization = sAuthorization,
                iGroupID = iGroupID,
                iSchoolID = iSchoolID,
                iStudentID = iStudentID,
                iRouteID = iRouteID,
                dDateOfTravel = dDateOfTravel
            )
        ).fold(
            {
                postError(it)
            },
            {
                _checkDuplicateStudentAttendanceMutableData.postValue(it)
            }
        )
    }

    fun amendFleetStudentAttendance(onBoardAttendancePostItem: OnBoardPostFleetAttendanceParams) =
        launchIOCoroutine {
            amendFleetStudentAttendanceInfoUseCase(onBoardAttendancePostItem).fold(
                {
                    postError(it)
                },
                {
                    _amendFleetStudentAttendanceMutableData.postValue(it)
                }
            )
        }

    fun postFleetStudentAttendance(onBoardAttendancePostItem: OnBoardPostFleetAttendanceParams) =
        launchIOCoroutine {
            postFleetStudentAttendanceInfoUseCase(onBoardAttendancePostItem).fold(
                {
                    postError(it)
                },
                {
                    _postFleetStudentAttendanceMutableData.postValue(it)
                }
            )
        }

    fun postFleetStudentAttendanceManually(onBoardPostFleetAttendanceManuallyParams: OnBoardPostFleetAttendanceManuallyParams) =
        launchIOCoroutine {
            postFleetStudentAttendanceManuallyUseCase(onBoardPostFleetAttendanceManuallyParams).fold(
                {
                    postError(it)
                },
                {
                    _postFleetStudentAttendanceManuallyMutableData.postValue(it)
                }
            )
        }
}