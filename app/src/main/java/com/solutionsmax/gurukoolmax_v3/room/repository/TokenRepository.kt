package com.solutionsmax.gurukoolmax_v3.room.repository

import com.solutionsmax.gurukoolmax_v3.room.dao.TokenDAO
import com.solutionsmax.gurukoolmax_v3.room.entity.TokenItem
import javax.inject.Inject


class TokenRepository @Inject constructor(
    private val tokenDAO: TokenDAO
) {

    suspend fun insertToken(tokenItem: TokenItem): Long =
        tokenDAO.postTokenInfo(tokenItem)

    suspend fun amendToken(tokenItem: TokenItem) =
        tokenDAO.amendTokenInfo(tokenItem)

    suspend fun purgeToken(tokenItem: TokenItem) =
        tokenDAO.purgeTokenInfo(tokenItem)

    suspend fun deleteAllTokens() = tokenDAO.deleteAllTokens()

    suspend fun retrieveToken(): List<TokenItem> =
        tokenDAO.retrieveTokenList()
}
