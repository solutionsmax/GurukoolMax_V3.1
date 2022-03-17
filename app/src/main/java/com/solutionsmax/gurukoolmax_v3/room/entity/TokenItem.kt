package com.solutionsmax.gurukoolmax_v3.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "token")
data class TokenItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "access_token")
    val access_token: String,
    @ColumnInfo(name = "token_type")
    val token_type: String,
    @SerializedName("expires_in")
    val expires_in: Int
)