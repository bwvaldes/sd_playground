package com.scubadeving.sd_playground.data.gear

import java.util.Calendar

data class Gear(
    val name: String? = null,
    val imageUrl: String? = null,
    val iconUrl: String? = null,
    val description: String? = null,
    val lastMaintenanceDate: String? = null, // Converter
    val maintenanceInterval: Int? = null
) {
    /**
     * Determines if the gear should be serviced. Returns true if [since]'s date > date of last
     * maintenance + maintenance interval; false otherwise.
     */
    fun maintenanceCheckDue(since: Calendar, lastMaintenanceDate: Calendar) =
        since > lastMaintenanceDate.apply { add(Calendar.DAY_OF_YEAR, maintenanceInterval!!) }
}
