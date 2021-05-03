package com.agenda.android.data.dao

import androidx.room.*
import com.agenda.android.data.entity.Scheduling

@Dao
interface SchedulingDao {

    @Insert
    suspend fun save(scheduling: Scheduling);

    @Update
    suspend fun update(scheduling: Scheduling)

    @Delete
    suspend fun delete(scheduling: Scheduling)

    @Query("select * from scheduling")
    suspend fun findAll(): List<Scheduling>

    @Query("select * from scheduling where id = :schedulingId")
    suspend fun findById(schedulingId: Int): Scheduling
}