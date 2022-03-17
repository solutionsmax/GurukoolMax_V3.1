package com.solutionsmax.gurukoolmax_v3.room.interactors.token

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.room.entity.TokenItem
import com.solutionsmax.gurukoolmax_v3.room.repository.TokenRepository
import javax.inject.Inject

class InsertTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) : UseCase<Long, TokenItem>() {

    override suspend fun run(params: TokenItem): Either<Failure, Long> {
        return Either.Right(tokenRepository.insertToken(params))
    }
}