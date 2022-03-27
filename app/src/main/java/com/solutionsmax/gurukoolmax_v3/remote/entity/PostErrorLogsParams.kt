package com.solutionsmax.gurukoolmax_v3.remote.entity

import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems

data class PostErrorLogsParams(
    val url: String,
    val sAuthorization: String,
    val postErrorLogsItems: PostErrorLogsItems
)