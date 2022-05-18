package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.cce_scholastic

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.cce_scholastic.RetrieveCceScholasticItem
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveGradeByListScholasticGrading
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.CceScholasticRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RetrieveGradeByListScholasticGradingUseCase @Inject constructor(
    private val cceScholasticRepository: CceScholasticRepository
) : FlowUseCase<List<RetrieveCceScholasticItem>, RetrieveGradeByListScholasticGrading>() {
    override suspend fun run(params: RetrieveGradeByListScholasticGrading): Flow<Either<Failure, List<RetrieveCceScholasticItem>>> {
        return flowOf(
            cceScholasticRepository.retrieveGradeByListScholasticGrading(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassID = params.iClassID,
                iMarks = params.iMarks,
                iStatusID = params.iStatusID
            )
        )
    }
}