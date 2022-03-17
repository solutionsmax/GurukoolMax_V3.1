package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fuel_logs

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fuel_log.FuelLogsRetrieveItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsRetrieveParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FuelLogsRepository
import javax.inject.Inject

class RetrieveFuelLogsDetailsUseCase @Inject constructor(
    private val fuelLogsRepository: FuelLogsRepository
) : UseCase<List<FuelLogsRetrieveItems>, FuelLogsRetrieveParams>() {
    override suspend fun run(params: FuelLogsRetrieveParams): Either<Failure, List<FuelLogsRetrieveItems>> {
        return fuelLogsRepository.retrieveFuelLogDetails(
            url = params.url,
            sAuthorization = params.sAuthorization,
            id = params.id
        )
    }
}