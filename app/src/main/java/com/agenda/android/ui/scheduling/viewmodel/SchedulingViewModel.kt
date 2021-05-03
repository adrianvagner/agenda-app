package com.agenda.android.ui.scheduling.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agenda.android.data.entity.Scheduling
import com.agenda.android.dto.scheduling.SchedulingDto
import com.agenda.android.repository.SchedulingRepository
import kotlinx.coroutines.launch

class SchedulingViewModel : ViewModel() {

    lateinit var scheduling: SchedulingDto;

    private val repository = SchedulingRepository();

    fun update(schedulingDto: SchedulingDto) {

        var scheduling = Scheduling(
            schedulingDto.id,
            schedulingDto.patientName,
            schedulingDto.consultationDate,
            schedulingDto.state,
            schedulingDto.procedureDescription,
        )

        viewModelScope.launch {
            repository.update(scheduling);
        }
    }

}