package com.scubadeving.sd_playground.data.model.certification

data class Prerequisites(
    val diveCount: Int? = null,
    val ageRequirement: Int? = null,
    val catalogCertificationRequirements: List<CatalogCertification>? = null
)
