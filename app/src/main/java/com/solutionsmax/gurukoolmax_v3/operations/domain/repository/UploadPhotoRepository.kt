package com.solutionsmax.gurukoolmax_v3.operations.domain.repository

import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.remote.FleetApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class UploadPhotoRepository @Inject constructor(
    private val fleetApi: FleetApi
) {

    suspend fun uploadImages(
        url: String,
        sAuthorization: String,
        file: MultipartBody.Part,
        content: Map<String, RequestBody>
    ): Either<Failure, ResponseBody> =
        Either.Right(
            fleetApi.uploadPhoto(
                url = url,
                sAuthorization = sAuthorization,
                file = file,
                content = content
            )
        )
}