package com.solutionsmax.gurukoolmax_v3.remote.interactor

import com.solutionsmax.gurukoolmax_v3.core.data.token.TokenEntity
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.remote.repository.RemoteTokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val remoteTokenRepository: RemoteTokenRepository
) : UseCase<TokenEntity, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, TokenEntity> =
        remoteTokenRepository.getRemoteTokenTest()
}