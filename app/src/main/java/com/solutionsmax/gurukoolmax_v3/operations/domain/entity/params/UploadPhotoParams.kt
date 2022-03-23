package com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params

import android.content.Context
import okhttp3.MultipartBody
import okhttp3.RequestBody

data class UploadPhotoParams(
    val url: String,
    val sAuthorization: String,
    val context: Context,
    val sImagePath: String,
    var file: MultipartBody.Part?,
    var content: Map<String, RequestBody>?
)
