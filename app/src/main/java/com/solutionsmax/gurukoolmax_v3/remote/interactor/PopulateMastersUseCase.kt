package com.solutionsmax.gurukoolmax_v3.remote.interactor

import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.core.data.master.params.PopulateMastersParams
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.remote.repository.MastersRepository
import javax.inject.Inject


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

class PopulateHourUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateHH(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateMinutesUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateMM(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateTimeCycleUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateTimeCycle(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateBoardUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateBoard(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateKmContentType @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateKmContentType(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateKmSubmissionCategory @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateKmSubmissionCategory(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateKmFormatTypes @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateKmFormatTypes(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateAcademicYear @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateAcademicYear(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateFocusAssignment @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateFocusAssignment(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}

class PopulateApprovalStatusUseCase @Inject constructor(
    private val mastersRepository: MastersRepository
) : UseCase<MutableList<PopulateMasterListItem>, PopulateMastersParams>() {
    override suspend fun run(params: PopulateMastersParams): Either<Failure, MutableList<PopulateMasterListItem>> {
        return mastersRepository.populateApprovalStatus(
            url = params.url,
            sAuthorization = params.sAuthorization,
            sTableName = params.sTableName
        )
    }
}