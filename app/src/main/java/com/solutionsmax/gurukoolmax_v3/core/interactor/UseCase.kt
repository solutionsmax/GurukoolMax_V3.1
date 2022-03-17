package com.solutionsmax.gurukoolmax_v3.core.interactor

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.ControlledRunner
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

abstract class UseCase<out Type, in Params> where Type : Any {

    private val controlledRunner: ControlledRunner<Either<Failure, Type>> by lazy { ControlledRunner() }
    protected val resultChannel = Channel<Any>()

    abstract suspend fun run(params: Params): Either<Failure, Type>


    suspend operator fun invoke(
        params: Params,
        cancelPrevious: Boolean = false
    ): Either<Failure, Type> =
        withContext(Dispatchers.IO) {
            when {
                cancelPrevious -> controlledRunner.cancelPreviousThenRun { performCatching(params) }
                else -> controlledRunner.joinPreviousOrRun { performCatching(params) }
            }
        }

    private suspend fun performCatching(params: Params): Either<Failure, Type> {
        return kotlin.runCatching { run(params) }.getOrElse { throwable ->
            buildFailure(throwable)
        }
    }

    protected fun buildFailure(throwable: Throwable): Either<Failure, Type> {
        return Either.Left(Failure.Internal(cause = throwable))
    }
}