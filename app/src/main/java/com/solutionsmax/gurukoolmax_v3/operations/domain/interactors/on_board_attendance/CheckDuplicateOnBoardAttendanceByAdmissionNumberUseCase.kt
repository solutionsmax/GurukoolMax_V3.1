package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.on_board_attendance

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance.OnBoardCheckDuplicateAttendanceByAdmissionNumberParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.StudentOnBoardAttendanceRepository
import javax.inject.Inject

class CheckDuplicateOnBoardAttendanceByAdmissionNumberUseCase @Inject constructor(
    private val studentOnBoardAttendanceRepository: StudentOnBoardAttendanceRepository
) : UseCase<Int, OnBoardCheckDuplicateAttendanceByAdmissionNumberParams>() {
    override suspend fun run(params: OnBoardCheckDuplicateAttendanceByAdmissionNumberParams): Either<Failure, Int> {
        return studentOnBoardAttendanceRepository.checkDuplicateOnBoardAttendanceByAdmissionNumber(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            sAdmissionNumber = params.sAdmissionNum,
            iRouteID = params.iRouteID,
            dDateOfTravel = params.dDateOfTravel
        )
    }
}