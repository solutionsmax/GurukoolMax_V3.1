package com.solutionsmax.gurukoolmax_v3.room.dao

import androidx.room.*
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Either
import com.solutionsmax.gurukoolmax_v3.room.entity.TokenItem

@Dao
interface TokenDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun postTokenInfo(tokenItem: TokenItem): Long

    @Update
    suspend fun amendTokenInfo(tokenItem: TokenItem)

    @Delete
    suspend fun purgeTokenInfo(tokenItem: TokenItem)

    @Query("DELETE FROM token")
    suspend fun deleteAllTokens()

    @Query("SELECT * FROM token ORDER BY id DESC")
    suspend fun retrieveTokenList(): List<TokenItem>
}