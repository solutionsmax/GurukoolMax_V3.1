package com.solutionsmax.gurukoolmax_v3.operations.domain.interactors.fleet_register

import androidx.core.content.FileProvider
import com.solutionsmax.gurukoolmax_v3.BuildConfig
import com.solutionsmax.gurukoolmax_v3.core.common.PhotoConstants.MEDIA_BUS_IMAGE
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.core.interactor.UseCase
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.UploadPhotoParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.repository.UploadPhotoRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class UploadFleetImageUseCase @Inject constructor(
    private val uploadPhotoRepository: UploadPhotoRepository
) : UseCase<ResponseBody, UploadPhotoParams>() {
    private var date: String = ""
    override suspend fun run(params: UploadPhotoParams): Either<Failure, ResponseBody> {

        date = SimpleDateFormat("ddMMyyyyhhmmss", Locale.getDefault()).format(Date())
        val file = File(params.sImagePath)

        val uri = FileProvider.getUriForFile(
            params.context,
            BuildConfig.APPLICATION_ID + ".provider",
            file
        )

        val requestFile =
            file.asRequestBody(params.context.contentResolver.getType(uri)?.toMediaTypeOrNull())

        val body: MultipartBody.Part = MultipartBody.Part.createFormData(
            "picture",
            date + file.name, requestFile
        )

        val items = HashMap<String, RequestBody>()
        items["FileName"] =
            (date + file.name).toRequestBody("multipart/form-data".toMediaTypeOrNull())
        items["FolderName"] =
            MEDIA_BUS_IMAGE.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        return uploadPhotoRepository.uploadImages(
            url = params.url,
            sAuthorization = params.sAuthorization,
            file = body,
            content = items
        )
    }
}