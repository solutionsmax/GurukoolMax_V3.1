package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.km

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.km.RetrieveKMInfo
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.KmRetrieveSearchParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.KmRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RetrieveKmSearchUseCase @Inject constructor(
    private val kmRepository: KmRepository
) : FlowUseCase<List<RetrieveKMInfo>, KmRetrieveSearchParams>() {
    override suspend fun run(params: KmRetrieveSearchParams): Flow<Either<Failure, List<RetrieveKMInfo>>> {
        return flowOf(
            kmRepository.retrieveKMSearch(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iClassStandardID = params.iClassStandardID,
                sSearchKey = params.sSearchKey,
                iStatusID = params.iStatusID
            )
        )
    }
}