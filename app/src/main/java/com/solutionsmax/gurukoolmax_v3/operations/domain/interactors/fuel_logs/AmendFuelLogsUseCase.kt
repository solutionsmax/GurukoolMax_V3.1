package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fuel_logs

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsPostParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FuelLogsRepository
import javax.inject.Inject

class AmendFuelLogsUseCase @Inject constructor(
    private val fuelLogsRepository: FuelLogsRepository
):UseCase<Int,FuelLogsPostParams>() {
    override suspend fun run(params: FuelLogsPostParams): Either<Failure, Int> {
        return fuelLogsRepository.amendFuelLogs(
            url = params.url,
            sAuthorization = params.sAuthorization,
            postFuelLogsItem = params.fuelLogsPostInfoItem
        )
    }
}