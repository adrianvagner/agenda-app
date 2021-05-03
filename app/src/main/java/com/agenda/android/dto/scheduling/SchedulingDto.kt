package com.agenda.android.dto.scheduling

import java.io.Serializable


class SchedulingDto(
    val id: Int,
    val patientName: String,
    val consultationDate: String,
    val state: String,
    val procedureDescription: String,
) : Serializable