package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.km

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.RetrieveKMInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.KmRetrieveListParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.KmRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RetrieveKmListUseCase @Inject constructor(
    private val kmRepository: KmRepository
) : FlowUseCase<List<RetrieveKMInfo>, KmRetrieveListParams>() {
    override suspend fun run(params: KmRetrieveListParams): Flow<Either<Failure, List<RetrieveKMInfo>>> {
        return flowOf(
            kmRepository.retrieveKMList(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassStandardID = params.iClassStandardID,
                iSubjectID = params.iSubjectID,
                iFormateTypeID = params.iFormateTypeID,
                iContentTypeID = params.iContentTypeID,
                iSourceID = params.iSourceID,
                iSubmitterID = params.iSubmitterID,
                iStatusID = params.iStatusID
            )
        )
    }
}