package com.agenda.android.repository

import com.agenda.android.data.db.SchedulingDb
import com.agenda.android.data.entity.Scheduling
import com.agenda.android.dto.scheduling.RegisterSchedulingDto
import com.agenda.android.dto.scheduling.SchedulingDto


class SchedulingRepository {

    private val dao = SchedulingDb.instance.schedulingDao()

    suspend fun save(scheduling: RegisterSchedulingDto) {

        val entity = Scheduling(
            scheduling.patientName,
            scheduling.consultationDate,
            scheduling.state,
            scheduling.procedureDescription
        );
        dao.save(entity);

    }

    suspend fun findById(schedulingId: Int): SchedulingDto {
        val scheduling = dao.findById(schedulingId)

        return SchedulingDto(
            scheduling.id ?: 0,
            scheduling.patientName,
            scheduling.consultationDate,
            scheduling.state,
            scheduling.procedureDescription
        )
    }


    suspend fun update(scheduling: Scheduling) {
        dao.update(scheduling);
    }


    suspend fun findAll(): List<SchedulingDto> {
        val schedules = dao.findAll();

        return schedules.map { scheduling ->
            SchedulingDto(
                scheduling.id ?: 0,
                scheduling.patientName,
                scheduling.consultationDate,
                scheduling.state,
                scheduling.procedureDescription
            )

        }
    }


}