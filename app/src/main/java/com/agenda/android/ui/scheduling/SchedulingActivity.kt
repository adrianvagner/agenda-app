package com.agenda.android.ui.scheduling

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.agenda.android.databinding.ActivitySchedulingBinding
import com.agenda.android.dto.scheduling.SchedulingDto
import com.agenda.android.ui.scheduling.contract.SchedulingContract
import com.agenda.android.ui.scheduling.viewmodel.SchedulingViewModel
import com.agenda.android.utils.PATIENTE_ATTENDED
import com.agenda.android.utils.PATIENTE_MISSING

class SchedulingActivity : AppCompatActivity() {


    private val viewModel by viewModels<SchedulingViewModel>();

    private val binding by lazy {
        ActivitySchedulingBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        val scheduling =
            intent.getSerializableExtra(SchedulingContract.EXTRA_SCHEDULING) as SchedulingDto

        viewModel.scheduling = scheduling

        binding.tvPastienteName.text = viewModel.scheduling.patientName
        binding.tvDate.text = viewModel.scheduling.consultationDate;
        binding.tvProcedure.text = viewModel.scheduling.procedureDescription;

        setupListeners();
    }



    private fun setupListeners() {

        binding.btnRegisterAttended.setOnClickListener { updateState(PATIENTE_ATTENDED) }

        binding.btnRegisterMissing.setOnClickListener { updateState(PATIENTE_MISSING) }
    }

    private fun updateState(state: Int){

        var scheduling = viewModel.scheduling;

        scheduling.state = state;

        viewModel.update(scheduling);
    }

}