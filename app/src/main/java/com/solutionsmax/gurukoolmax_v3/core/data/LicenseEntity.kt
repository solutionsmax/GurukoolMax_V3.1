package com.solutionsmax.gurukoolmax_v3.core.data

import com.google.gson.annotations.SerializedName

data class LicenseEntity(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("GROUP_ID")
    val group_id: Int,
    @SerializedName("GROUP_NAME")
    val group_name: String,
    @SerializedName("BRANCH_ID")
    val branch_id: Int,
    @SerializedName("BRANCH_NAME")
    val branch_name: String,
    @SerializedName("COUNTRY_ID")
    val country_id: Int,
    @SerializedName("STATE_ID")
    val state_id: Int,
    @SerializedName("CITY_ID")
    val city_id: Int,
    @SerializedName("SITE_CODE")
    val site_code: String,
    @SerializedName("REST_URL")
    val rest_url: String,
    @SerializedName("ASSET_URL")
    val asset_url: String,
    @SerializedName("LICENSE_KEY")
    val license_key: String,
    @SerializedName("CONTACT_NAME")
    val contact_name: String,
    @SerializedName("CONTACT_NUMBER")
    val contact_number: String,
    @SerializedName("CONTACT_EMAIL")
    val contact_mail: String,
    @SerializedName("WORKFLOW_STATUS_ID")
    val workflow_status_id: Int,
    @SerializedName("CREATE_DATE")
    val create_date: String
)
