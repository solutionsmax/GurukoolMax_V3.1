package com.solutionsmax.gurukoolmax_v3.remote.interactor

import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.core.data.master.params.PopulateMastersParams
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.remote.repository.MastersRepository
import javax.inject.Inject
import javax.inject.Singleton


class PopulateMastersUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateMasters(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateMakeUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateMake(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateManufactureYearUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateManufactureYear(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateFuelTypeUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateFuelType(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}