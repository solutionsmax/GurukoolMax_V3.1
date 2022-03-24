package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.on_board_attendance

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.on_board_attendance.OnBoardPostFleetAttendanceParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.StudentOnBoardAttendanceRepository
import javax.inject.Inject

class PostFleetStudentAttendanceInfoUseCase @Inject constructor(
    private val studentOnBoardAttendanceRepository: StudentOnBoardAttendanceRepository
) : UseCase<Int, OnBoardPostFleetAttendanceParams>() {
    override suspend fun run(params: OnBoardPostFleetAttendanceParams): Either<Failure, Int> {
        return studentOnBoardAttendanceRepository.postFleetAttendanceInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            onBoardAttendancePostItem = params.onBoardAttendancePostItem
        )
    }
}