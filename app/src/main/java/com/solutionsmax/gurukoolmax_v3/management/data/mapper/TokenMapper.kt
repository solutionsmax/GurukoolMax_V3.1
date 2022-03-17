package com.solutionsmax.gurukoolmax_v3.management.data.mapper

import com.solutionsmax.gurukoolmax_v3.core.data.token.TokenEntity
import com.solutionsmax.gurukoolmax_v3.room.entity.TokenItem

fun TokenEntity.toEntity(): TokenItem {
    return TokenItem(
        access_token = access_token,
        token_type = token_type,
        expires_in = expires_in
    )
}