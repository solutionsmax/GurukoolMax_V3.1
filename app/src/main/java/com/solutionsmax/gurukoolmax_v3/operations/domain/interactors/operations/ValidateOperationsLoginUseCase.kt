package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.operations

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.operations.ValidateOperationsLogin
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.OperationsRepository
import javax.inject.Inject

class ValidateOperationsLoginUseCase @Inject constructor(
    private val operationsRepository: OperationsRepository
) : UseCase<Int, ValidateOperationsLogin>() {
    override suspend fun run(params: ValidateOperationsLogin): Either<Failure, Int> {
        return operationsRepository.validateOperationsLogin(
            url = params.url,
            sAuthorization = params.sAuthorization,
            iGroupID = params.iGroupID,
            iSchoolID = params.iSchoolID,
            sUsername = params.sUsername,
            sPassword = params.sPassword
        )
    }
}