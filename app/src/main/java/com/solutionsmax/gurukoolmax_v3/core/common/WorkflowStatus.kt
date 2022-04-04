package com.solutionsmax.gurukoolmax_v3.core.common

object WorkflowStatus {
    
    private val workflowStatusMapping = mapOf(
        -1 to "ALL",
        1 to "DRAFT",
        2 to "ON HOLD",
        3 to "SUBMITTED FOR REVIEW",
        4 to "APPROVED",
        5 to "RELEASE",
        6 to "REJECT",
        7 to "DELETE",
        8 to "FACULTY REVIEW",
    )

    fun setStatus(id: Int) = workflowStatusMapping[id]
}