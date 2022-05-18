package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.CheckDuplicateScholasticMaxMinGrading
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.CceScholasticRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import javax.inject.Inject

class CheckDuplicateScholasticGradingByMinRangeUseCase @Inject constructor(
    private val cceScholasticRepository: CceScholasticRepository
) : UseCase<Int, CheckDuplicateScholasticMaxMinGrading>() {
    override suspend fun run(params: CheckDuplicateScholasticMaxMinGrading): Either<Failure, Int> {
        return cceScholasticRepository.checkDuplicateScholasticGradingByMinRange(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iBoardID = params.iBoardID,
            iClassID = params.iClassID,
            iRangeValue = params.iRangeValue
        )
    }
}