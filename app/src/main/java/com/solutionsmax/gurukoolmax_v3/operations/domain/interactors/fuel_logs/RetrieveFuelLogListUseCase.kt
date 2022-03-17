package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fuel_logs

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsRetrieveListParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FuelLogsRepository
import javax.inject.Inject

class RetrieveFuelLogListUseCase @Inject constructor(
    private val fuelLogsRepository: FuelLogsRepository
):UseCase<List<FuelLogsRetrieveItems>, FuelLogsRetrieveListParams>() {
    override suspend fun run(params: FuelLogsRetrieveListParams): Either<Failure, List<FuelLogsRetrieveItems>> {
        return fuelLogsRepository.retrieveFuelLogList(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iFleetID = params.iFleetID,
            iWorkflowStatusID = params.iWorkflowStatusID
        )
    }
}