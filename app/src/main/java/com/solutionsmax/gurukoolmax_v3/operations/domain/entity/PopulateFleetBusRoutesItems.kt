package com.solutionsmax.gurukoolmax_v3.operations.domain.entity

import com.google.gson.annotations.SerializedName

data class PopulateFleetBusRoutesItems(
    @SerializedName("ID")
    val id:Int,
    @SerializedName("ROUTE_NAME")
    val sRouteName:String
){
    override fun toString(): String {
        return sRouteName
    }
}