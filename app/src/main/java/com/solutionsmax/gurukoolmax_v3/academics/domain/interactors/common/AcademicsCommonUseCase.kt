package com.solutionsmax.gurukoolmax_v3.academics.domain.interactors.common

import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.common.PopulateClassItems
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateClassParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.PopulateSemesterClassParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.repository.common.AcademicsCommonRepository
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PopulateSemesterClassUseCase @Inject constructor(
    private val academicsCommonRepository: AcademicsCommonRepository
) : FlowUseCase<MutableList<PopulateClassItems>, PopulateSemesterClassParams>() {
    override suspend fun run(params: PopulateSemesterClassParams): Flow<Either<Failure, MutableList<PopulateClassItems>>> {
        return flowOf(
            academicsCommonRepository.populateClassSemester(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iStatusID = params.iStatusID
            )
        )
    }
}

class PopulateClassUseCase @Inject constructor(
    private val academicsCommonRepository: AcademicsCommonRepository
) : FlowUseCase<MutableList<PopulateClassItems>, PopulateClassParams>() {
    override suspend fun run(params: PopulateClassParams): Flow<Either<Failure, MutableList<PopulateClassItems>>> {
        return flowOf(
            academicsCommonRepository.populateClass(
                url = params.url,
                sAuthorization = params.sAuthorization,
                iGroupID = params.iGroupID,
                iSchoolID = params.iSchoolID,
                iBoardID = params.iBoardID,
                iYearID = params.iYearId,
                iStatusID = params.iStatusID
            )
        )
    }
}