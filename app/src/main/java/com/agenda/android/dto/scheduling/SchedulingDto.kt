package com.agenda.android.dto.scheduling

import java.io.Serializable


class SchedulingDto(
    val id: Int,
    val patientName: String,
    val consultationDate: String,
    var state: Int,
    val procedureDescription: String,
) : Serializable