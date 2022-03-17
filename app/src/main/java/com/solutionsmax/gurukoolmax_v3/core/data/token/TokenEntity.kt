package com.solutionsmax.gurukoolmax_v3.core.data.token

import com.google.gson.annotations.SerializedName

data class TokenEntity(
    @SerializedName("access_token")
    val access_token: String,
    @SerializedName("token_type")
    val token_type: String,
    @SerializedName("expires_in")
    val expires_in: Int
)
