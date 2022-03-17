package com.solutionsmax.gurukoolmax_v3.room.interactors.token

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.room.repository.TokenRepository
import javax.inject.Inject

class DeleteAllTokensUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) : UseCase<Unit, Unit>() {
    override suspend fun run(params: Unit): Either<Failure, Unit> {
        return Either.Right(tokenRepository.deleteAllTokens())
    }
}