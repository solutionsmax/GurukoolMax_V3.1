package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.km

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.KmPostFileNameParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.KmRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class PostKmFileNameUseCase @Inject constructor(
    private val kmRepository: KmRepository
) : UseCase<Int, KmPostFileNameParams>() {
    override suspend fun run(params: KmPostFileNameParams): Either<Failure, Int> {
        return kmRepository.postFileNameKMInfo(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sFileName = params.sFileName,
            id = params.id
        )
    }
}