package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.km

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.KmRemoveInfoParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.KmRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class RemoveKmInfoUseCase @Inject constructor(
    private val kmRepository: KmRepository
) : UseCase<Int, KmRemoveInfoParams>() {
    override suspend fun run(params: KmRemoveInfoParams): Either<Failure, Int> {
        return kmRepository.removeKMInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id,
            iSubmitterID = params.iSubmitterID
        )
    }
}