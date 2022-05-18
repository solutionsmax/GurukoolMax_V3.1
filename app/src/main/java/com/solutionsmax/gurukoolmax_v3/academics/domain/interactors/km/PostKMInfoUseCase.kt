package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.km

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.KmPostParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.KmRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class PostKMInfoUseCase @Inject constructor(
    private val kmRepository: KmRepository
) : UseCase<Int, KmPostParams>() {
    override suspend fun run(params: KmPostParams): Either<Failure, Int> {
        return kmRepository.postKMInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postKMInfo = params.postKMInfo
        )
    }
}