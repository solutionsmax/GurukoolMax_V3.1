package com.solutionsmax.gurukoolmax_v3.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "license")
data class LicenseItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "group_id")
    val group_id: Int,

    @ColumnInfo(name = "group_name")
    val group_name: String,

    @ColumnInfo(name = "branch_id")
    val branch_id: Int,

    @ColumnInfo(name = "branch_name")
    val branch_name: String,

    @ColumnInfo(name = "country_id")
    val country_id: Int,

    @ColumnInfo(name = "state_id")
    val state_id: Int,

    @ColumnInfo(name = "city_id")
    val city_id: Int,

    @ColumnInfo(name = "site_code")
    val site_code: String,

    @ColumnInfo(name = "rest_url")
    val rest_url: String,

    @ColumnInfo(name = "asset_url")
    val asset_url: String,

    @ColumnInfo(name = "license_key")
    val license_key: String,

    @ColumnInfo(name = "contact_name")
    val contact_name: String,

    @ColumnInfo(name = "contact_mail")
    val contact_mail: String,

    @ColumnInfo(name = "workflow_status_id")
    val workflow_status_id: Int,

    @ColumnInfo(name = "create_date")
    val create_date: String
)