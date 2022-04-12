package com.solutionsmax.gurukoolmax_v3.core.data.mapper

import com.solutionsmax.gurukoolmax_v3.core.data.LicenseEntity
import com.solutionsmax.gurukoolmax_v3.room.entity.LicenseItem

fun LicenseEntity.toItem(): LicenseItem {
    return LicenseItem(
        group_id = group_id,
        group_name = group_name,
        branch_id = branch_id,
        branch_name = branch_name,
        country_id = country_id,
        state_id = state_id,
        city_id = city_id,
        site_code = site_code,
        rest_url = rest_url,
        asset_url = asset_url,
        license_key = license_key,
        contact_name = contact_name,
        contact_mail = contact_mail,
        workflow_status_id = workflow_status_id,
        create_date = create_date
    )
}

fun LicenseEntity.toItemList(): List<LicenseItem> {
    return listOf(
        LicenseItem(
            group_id = group_id,
            group_name = group_name,
            branch_id = branch_id,
            branch_name = branch_name,
            country_id = country_id,
            state_id = state_id,
            city_id = city_id,
            site_code = site_code,
            rest_url = rest_url,
            asset_url = asset_url,
            license_key = license_key,
            contact_name = contact_name,
            contact_mail = contact_mail,
            workflow_status_id = workflow_status_id,
            create_date = create_date
        )
    )
}