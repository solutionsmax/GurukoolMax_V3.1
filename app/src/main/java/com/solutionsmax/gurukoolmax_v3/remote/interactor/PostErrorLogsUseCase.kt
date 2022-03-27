package com.solutionsmax.gurukoolmax_v3.remote.interactor

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.remote.entity.PostErrorLogsParams
import com.solutionsmax.gurukoolmax_v3.remote.repository.ErrorLogsRepository
import javax.inject.Inject

class PostErrorLogsUseCase @Inject constructor(
    private val errorLogsRepository: ErrorLogsRepository
) : UseCase<Int, PostErrorLogsParams>() {
    override suspend fun run(params: PostErrorLogsParams): Either<Failure, Int> {
        return errorLogsRepository.postErrorLogs(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postErrorLogsItems = params.postErrorLogsItems
        )
    }
}