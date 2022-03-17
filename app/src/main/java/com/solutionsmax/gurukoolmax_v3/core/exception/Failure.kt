package com.solutionsmax.gurukoolmax_v3.core.exception

sealed class Failure(
    message: String?,
    cause: Throwable?
) : Throwable(message ?: cause?.message ?: "Message Field is empty", cause) {

    class Internal(message: String? = null, cause: Throwable? = null) : Failure(message, cause)

    class Remote(message: String? = null, cause: Throwable? = null): Failure(message, cause)
}
