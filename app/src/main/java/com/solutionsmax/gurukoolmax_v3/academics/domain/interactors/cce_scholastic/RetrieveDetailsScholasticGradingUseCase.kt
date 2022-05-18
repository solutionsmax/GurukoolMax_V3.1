package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.RetrieveCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.IdScholasticGrading
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.CceScholasticRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class RetrieveDetailsScholasticGradingUseCase @Inject constructor(
    private val cceScholasticRepository: CceScholasticRepository
) : UseCase<List<RetrieveCceScholasticItem>, IdScholasticGrading>() {
    override suspend fun run(params: IdScholasticGrading): Either<Failure, List<RetrieveCceScholasticItem>> {
        return cceScholasticRepository.retrieveDetailsScholasticGrading(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}