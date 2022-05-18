package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PostCceScholasticParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.CceScholasticRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class PostCceScholasticInfoUseCase @Inject constructor(
    private val cceScholasticRepository: CceScholasticRepository
) : UseCase<Int, PostCceScholasticParams>() {
    override suspend fun run(params: PostCceScholasticParams): Either<Failure, Int> {
        return cceScholasticRepository.postScholasticGrading(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postCceScholasticItem = params.postCceScholasticItem
        )
    }
}