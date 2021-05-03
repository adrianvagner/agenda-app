package com.agenda.android.ui.register

import android.app.DatePickerDialog
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
import com.agenda.android.utils.PATIENTE_SCHEDULED
import java.text.SimpleDateFormat
import java.util.*

class RegisterSchedulingActivity : AppCompatActivity() {


    private var expenseDate = Date();

    private val viewModel by viewModels<RegisterSchedulingViewModel>();

    private val binding: ActivityRegisterSchedulingBinding by lazy {
        ActivityRegisterSchedulingBinding.inflate(layoutInflater);
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.onSaveComplete.observe(this, { onSaveComplete() })

        setupEvents();
    }


    private fun setupEvents() {
        binding.etDate.setOnClickListener { showDateDialog() }
    }

    private fun showDateDialog() {
        val locale = Locale("pt", "BR")
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", locale)

        val dialog = DatePickerDialog(this)
        dialog.setOnDateSetListener { _, year, month, day ->
            val cal = Calendar.getInstance(locale)
            cal.set(year, month, day)

            expenseDate = cal.time
            binding.etDate.setText(dateFormat.format(expenseDate))
        }

        dialog.show()
    }



    private fun save() {
        if (validateInput()) {
            viewModel.save(
                RegisterSchedulingDto(
                    binding.etPatientName.text.toString(),
                    binding.etDate.text.toString(),
                    PATIENTE_SCHEDULED,
                    binding.etProcedure.text.toString(),
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

        binding.tilPatientName.error = if (binding.etPatientName.text.toString().trim().isEmpty()) {
            isValid = false
            getString(R.string.messageNameRequired)
        } else {
            null
        }

        binding.tilProcedure.error = if (binding.etProcedure.text.toString().trim().isEmpty()) {
            isValid = false
            getString(R.string.messageRequired)
        } else {
            null
        }

        binding.tilDate.error = if (binding.etDate.text.toString().trim().isEmpty()) {
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