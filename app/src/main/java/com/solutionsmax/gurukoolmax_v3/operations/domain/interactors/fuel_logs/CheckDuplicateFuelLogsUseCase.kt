package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fuel_logs

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fuel_logs.FuelLogsDuplicateParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.FuelLogsRepository
import javax.inject.Inject

class CheckDuplicateFuelLogsUseCase @Inject constructor(
    private val fuelLogsRepository: FuelLogsRepository
) : UseCase<Int, FuelLogsDuplicateParams>() {
    override suspend fun run(params: FuelLogsDuplicateParams): Either<Failure, Int> {
        return fuelLogsRepository.checkDuplicateFuelLogs(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            iFleetID = params.iFleetID,
            iOdometerID = params.iOdometerID
        )
    }
}