package com.agenda.android.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.agenda.android.R
import com.agenda.android.databinding.ActivityListSchedulingBinding
import com.agenda.android.dto.scheduling.SchedulingDto
import com.agenda.android.ui.list.adapter.ListSchedulingAdapter
import com.agenda.android.ui.list.viewmodel.ListSchedulingViewModel
import com.agenda.android.ui.register.RegisterSchedulingActivity
import com.agenda.android.ui.scheduling.contract.SchedulingContract

class ListSchedulingActivity : AppCompatActivity() {

    private val viewModel: ListSchedulingViewModel by viewModels();

    private lateinit var adapter: ListSchedulingAdapter;

    private val binding: ActivityListSchedulingBinding by lazy {
        ActivityListSchedulingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupList()
        setupEvents()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_scheduling, menu)
        return true
    }

    private fun setupEvents() {
        viewModel.listScheduling.observe(this) { medicines ->
            this.adapter.items = medicines
        }
        viewModel.list()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnAdd) {
            registerResult.launch(RegisterSchedulingActivity.newIntent(this))
            return true
        }

        return false
    }


    private val registerResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            viewModel.list()
        }
    }


    private fun setupList() {
        adapter = ListSchedulingAdapter()
        adapter.onItemSelected = ::onSchedulingDtoSelected

        binding.rvScheduling.adapter = adapter
        binding.rvScheduling.layoutManager = LinearLayoutManager(this)
        binding.rvScheduling.addItemDecoration(
            DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun onSchedulingDtoSelected(medicine: SchedulingDto) {
        schedulingResult.launch(medicine)
    }


    private val schedulingResult = registerForActivityResult(SchedulingContract()) {
        viewModel.list()
    }



}