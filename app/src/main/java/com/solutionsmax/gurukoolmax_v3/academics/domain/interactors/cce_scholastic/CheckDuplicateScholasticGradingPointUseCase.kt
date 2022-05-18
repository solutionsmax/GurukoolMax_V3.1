package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.CheckDuplicateScholasticGradPointGrading
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.CceScholasticRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class CheckDuplicateScholasticGradingPointUseCase @Inject constructor(
    private val cceScholasticRepository: CceScholasticRepository
) : UseCase<Int, CheckDuplicateScholasticGradPointGrading>() {
    override suspend fun run(params: CheckDuplicateScholasticGradPointGrading): Either<Failure, Int> {
        return cceScholasticRepository.checkDuplicateScholasticGradingPoint(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            dGradePoint = params.dGradePoint
        )
    }
}