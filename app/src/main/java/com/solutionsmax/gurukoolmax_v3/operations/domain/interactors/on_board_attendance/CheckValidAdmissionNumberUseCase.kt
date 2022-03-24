package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.on_board_attendance

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance.OnBoardAttendanceParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.StudentOnBoardAttendanceRepository
import javax.inject.Inject

class CheckValidAdmissionNumberUseCase @Inject constructor(
    private val studentOnBoardAttendanceRepository: StudentOnBoardAttendanceRepository
) : UseCase<Int, OnBoardAttendanceParams>() {
    override suspend fun run(params: OnBoardAttendanceParams): Either<Failure, Int> {
        return studentOnBoardAttendanceRepository.checkValidAdmissionNumber(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            sAdmissionNum = params.sAdmissionNum
        )
    }
}