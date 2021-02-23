package com.scubadeving.sd_playground.data

data class Wildlife(
    val commonName: String,
    val scientificName: String = "Lorem Ipsum",
    val status: ConservationStatus = ConservationStatus.LEAST_CONCERN,
    val description: String = "A beautiful creature",
    val location: String = "Earth",
    val encounters: Encounters? = null
)

enum class ConservationStatus {
    NOT_EVALUATED,
    DATA_DEFICIENT,
    LEAST_CONCERN,
    NEAR_THREATENED,
    VULNERABLE,
    ENDANGERED,
    CRITICALLY_ENDANGERED,
    EXTINCT_IN_THE_WILD,
    EXTINCT
}

data class Encounters(
    val count: Int,
    val locations: List<String>
)
