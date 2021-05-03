package com.agenda.android.ui.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agenda.android.dto.scheduling.SchedulingDto
import com.agenda.android.repository.SchedulingRepository
import kotlinx.coroutines.launch

class ListSchedulingViewModel : ViewModel() {

    private val repository = SchedulingRepository();


    val listScheduling = MutableLiveData<List<SchedulingDto>>();

    fun list() {
        viewModelScope.launch {
            val result = repository.findAll();
            listScheduling.postValue(result);
        }
    }

}