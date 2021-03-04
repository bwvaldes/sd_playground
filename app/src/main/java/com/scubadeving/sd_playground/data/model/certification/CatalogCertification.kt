package com.scubadeving.sd_playground.data.model.certification

import com.scubadeving.sd_playground.data.model.certification.EligibilityStatus.UNAVAILABLE

data class CatalogCertification(
    val name: String? = null,
    val agency: String? = null,
    val specialties: List<Specialty>? = null,
    val prerequisites: Prerequisites? = null,
    val eligibilityStatus: EligibilityStatus = UNAVAILABLE
)