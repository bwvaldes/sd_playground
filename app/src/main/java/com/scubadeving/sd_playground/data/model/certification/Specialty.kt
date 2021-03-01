package com.scubadeving.sd_playground.data.model.certification

data class Specialty(
    val name: String? = null,
    val prerequisites: Prerequisites? = null,
    val eligibilityStatus: EligibilityStatus = EligibilityStatus.UNAVAILABLE
)
