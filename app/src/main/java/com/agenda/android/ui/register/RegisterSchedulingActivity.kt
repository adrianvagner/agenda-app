package com.agenda.android.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.agenda.android.R
import com.agenda.android.databinding.ActivityRegisterSchedulingBinding
import com.agenda.android.dto.scheduling.RegisterSchedulingDto
import com.agenda.android.ui.register.viewmodel.RegisterSchedulingViewModel

class RegisterSchedulingActivity : AppCompatActivity() {


    private val viewModel by viewModels<RegisterSchedulingViewModel>()

    private val binding: ActivityRegisterSchedulingBinding by lazy {
        ActivityRegisterSchedulingBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.onSaveComplete.observe(this, { onSaveComplete() })
    }



    private fun save() {
        if (validateInput()) {
            viewModel.save(
                RegisterSchedulingDto(
                    binding.atPatientName.text.toString(),
                    binding.atDate.text.toString(),
                    "AGENDADO",
                    binding.atProcedure.text.toString(),

                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_register_scheduling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnSave) {
            save()
            return true
        }

        return false
    }



    private fun validateInput(): Boolean {
        var isValid = true

        binding.atPatientName.error = if (binding.atPatientName.text.toString().trim().isEmpty()) {
            isValid = false
            getString(R.string.messageNameRequired)
        } else {
            null
        }

        binding.atProcedure.error = if (binding.atProcedure.text.toString().trim().isEmpty()) {
            isValid = false
            getString(R.string.messageRequired)
        } else {
            null
        }

        return isValid
    }

    private fun onSaveComplete() {
        setResult(RESULT_OK)
        finish()
    }



    companion object {
        fun newIntent(contex: Context) = Intent(contex, RegisterSchedulingActivity::class.java)

    }



}