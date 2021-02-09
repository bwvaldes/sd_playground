package com.scubadeving.sd_playground.data

data class Certification(
    val name: String,
    val specialties: List<Specialty>? = null,
    val prerequisites: Prerequisites? = null
)

data class Specialty(
    val name: String,
    val prerequisites: Prerequisites? = null
)

data class Prerequisites(
    val ageRequirement: Int? = null,
    val certificationRequirements: List<Certification>? = null
)
