package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.common.TokenConstants
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.on_board_attendance.OnBoardAttendancePostItem
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import javax.inject.Inject

class StudentOnBoardAttendanceRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    suspend fun checkValidAdmissionNumber(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        sAdmissionNum: String
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkValidAdmissionNumber(
                url, "${TokenConstants.BEARER} $sAuthorization", iGroupID, iSchoolID, sAdmissionNum
            )
        )

    suspend fun checkStudentAttendance(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStudentID: Int,
        sAdmissionNum: String,
        iRouteID: Int,
        dDateOfTravel: String
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkStudentAttendance(
                url,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iStudentID,
                sAdmissionNum,
                iRouteID,
                dDateOfTravel
            )
        )

    suspend fun checkDuplicateOnBoardAttendance(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStudentID: Int,
        iRouteID: Int,
        dDateOfTravel: String
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.checkDuplicateAttendance(
                url,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iStudentID,
                iRouteID,
                dDateOfTravel
            )
        )

    suspend fun checkDuplicateOnBoardAttendanceByAdmissionNumber(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        sAdmissionNumber: String,
        iRouteID: Int,
        dDateOfTravel: String
    ):Either<Failure,Int> =
        Either.Right(
            fleetApi.checkDuplicateAttendanceByAdmissionNumber(
                url,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                sAdmissionNumber,
                iRouteID,
                dDateOfTravel
            )
        )

    suspend fun postFleetAttendanceInfo(
        url: String,
        sAuthorization: String,
        onBoardAttendancePostItem: OnBoardAttendancePostItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postFleetStudentAttendance(
                url,
                "${TokenConstants.BEARER} $sAuthorization",
                onBoardAttendancePostItem
            )
        )

    suspend fun amendFleetAttendanceInfo(
        url: String,
        sAuthorization: String,
        onBoardAttendancePostItem: OnBoardAttendancePostItem
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.amendFleetStudentAttendance(
                url,
                "${TokenConstants.BEARER} $sAuthorization",
                onBoardAttendancePostItem
            )
        )

    suspend fun postFleetAttendanceInfoManually(
        url: String,
        sAuthorization: String,
        iGroupID: Int,
        iSchoolID: Int,
        iStudentID: Int,
        sAdmissionNum: String,
        iRouteID: Int,
        dDateOfTravel: String,
        iAttendanceStatusID: Int
    ): Either<Failure, Int> =
        Either.Right(
            fleetApi.postFleetStudentAttendanceManually(
                url,
                "${TokenConstants.BEARER} $sAuthorization",
                iGroupID,
                iSchoolID,
                iStudentID,
                sAdmissionNum,
                iRouteID,
                dDateOfTravel,
                iAttendanceStatusID
            )
        )

}