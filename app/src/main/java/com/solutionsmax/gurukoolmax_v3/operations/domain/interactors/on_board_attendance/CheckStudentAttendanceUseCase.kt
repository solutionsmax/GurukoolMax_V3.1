package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.on_board_attendance

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance.OnBoardCheckAttendanceParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.StudentOnBoardAttendanceRepository
import javax.inject.Inject

class CheckStudentAttendanceUseCase @Inject constructor(
    private val studentOnBoardAttendanceRepository: StudentOnBoardAttendanceRepository
) : UseCase<Int, OnBoardCheckAttendanceParams>() {
    override suspend fun run(params: OnBoardCheckAttendanceParams): Either<Failure, Int> {
       return studentOnBoardAttendanceRepository.checkStudentAttendance(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iStudentID = params.iStudentID,
            sAdmissionNum = params.sAdmissionNum,
            iRouteID = params.iRouteID,
            dDateOfTravel = params.dDateOfTravel
        )
    }
}