package com.agenda.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "scheduling")
data class Scheduling(
        val patientName: String,
        val consultationDate: String,
        val state: String,
        val procedureDescription: String,
){

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
