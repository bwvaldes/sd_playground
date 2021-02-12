package com.scubadeving.sd_playground.data

import com.scubadeving.sd_playground.data.EligibilityStatus.UNAVAILABLE

data class Certification(
    val name: String,
    val specialties: List<Specialty>? = null,
    val prerequisites: Prerequisites? = null,
    val eligibilityStatus: EligibilityStatus = UNAVAILABLE
)

enum class EligibilityStatus {
    UNAVAILABLE,
    ELIGIBLE,
    INELIGIBLE,
    COMPLETED
}

data class Specialty(
    val name: String,
    val prerequisites: Prerequisites? = null
)

data class Prerequisites(
    val ageRequirement: Int? = null,
    val certificationRequirements: List<Certification>? = null
)
