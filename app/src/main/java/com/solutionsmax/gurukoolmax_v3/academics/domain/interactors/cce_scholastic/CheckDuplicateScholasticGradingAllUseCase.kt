package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.CheckDuplicateScholasticGradingAll
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.CceScholasticRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class CheckDuplicateScholasticGradingAllUseCase @Inject constructor(
    private val cceScholasticRepository: CceScholasticRepository
) : UseCase<Int, CheckDuplicateScholasticGradingAll>() {
    override suspend fun run(params: CheckDuplicateScholasticGradingAll): Either<Failure, Int> {
        return cceScholasticRepository.checkDuplicateScholasticGradingAll(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            iMaxScore = params.iMaxScore,
            iMinScore = params.iMinScore,
            sGrade = params.sGrade,
            dGradePoint = params.dGradePoint
        )
    }
}