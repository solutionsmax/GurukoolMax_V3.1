package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.km

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.KmSetStatusParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.KmRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class SetStatusKMInfoUseCase @Inject constructor(
    private val kmRepository: KmRepository
) : UseCase<Int, KmSetStatusParams>() {
    override suspend fun run(params: KmSetStatusParams): Either<Failure, Int> {
        return kmRepository.setStatusKMInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iStatusID = params.iStatusID,
            id = params.id
        )
    }
}