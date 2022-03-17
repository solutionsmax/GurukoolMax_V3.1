package com.solutionsmax.gurukoolmax_v3.core.interactor

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

abstract class FlowUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Flow<Either<Failure, Type>>

    suspend operator fun invoke(params: Params): Flow<Either<Failure, Type>> = kotlin.runCatching {
        run(params)
    }.getOrElse { getFailureFlow(it) }

    protected open fun getFailureFlow(throwable: Throwable): Flow<Either<Failure, Type>> {
        return flowOf(Either.Left(Failure.Internal(cause = throwable)))
    }
}