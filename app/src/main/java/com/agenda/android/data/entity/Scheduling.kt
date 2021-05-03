package com.agenda.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "scheduling")
data class Scheduling(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val patientName: String,
    val consultationDate: String,
    val state: Int,
    val procedureDescription: String,
)







