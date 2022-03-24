package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.on_board_attendance

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance.OnBoardCheckDuplicateAttendanceParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.StudentOnBoardAttendanceRepository
import javax.inject.Inject

class CheckDuplicateStudentAttendanceUseCase @Inject constructor(
    private val studentOnBoardAttendanceRepository: StudentOnBoardAttendanceRepository
) : UseCase<Int, OnBoardCheckDuplicateAttendanceParams>() {
    override suspend fun run(params: OnBoardCheckDuplicateAttendanceParams): Either<Failure, Int> {
        return studentOnBoardAttendanceRepository.checkDuplicateOnBoardAttendance(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iStudentID = params.iStudentID,
            iRouteID = params.iRouteID,
            dDateOfTravel = params.dDateOfTravel
        )
    }
}