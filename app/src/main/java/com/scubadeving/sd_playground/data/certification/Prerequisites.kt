package com.scubadeving.sd_playground.data.certification

data class Prerequisites(
    val diveCount: Int? = null,
    val ageRequirement: Int? = null,
    val catalogCertificationRequirements: List<CatalogCertification>? = null
)
