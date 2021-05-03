package com.agenda.android.ui.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agenda.android.dto.scheduling.RegisterSchedulingDto
import com.agenda.android.repository.SchedulingRepository
import kotlinx.coroutines.launch

class RegisterSchedulingViewModel : ViewModel() {

    private val repository = SchedulingRepository();

    val onSaveComplete =  MutableLiveData<Unit>();

    fun save(shceduling: RegisterSchedulingDto) {
        viewModelScope.launch {
            repository.save(shceduling)
            onSaveComplete.postValue(Unit)
        }
    }






}