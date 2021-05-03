package com.agenda.android.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agenda.android.R
import com.agenda.android.databinding.ItemSchedulingBinding
import com.agenda.android.dto.scheduling.SchedulingDto
import com.agenda.android.utils.PATIENTE_ATTENDED
import com.agenda.android.utils.PATIENTE_MISSING

class ListSchedulingAdapter : RecyclerView.Adapter<ListSchedulingAdapter.SchedulingViewHolder>() {

    lateinit var onItemSelected: (SchedulingDto) -> Unit

    var items = listOf<SchedulingDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulingViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_scheduling, parent, false);
        return SchedulingViewHolder(itemView);
    }

    override fun onBindViewHolder(holder: SchedulingViewHolder, position: Int) {
        holder.bindItem(items[position], onItemSelected)
    }

    override fun getItemCount() = items.size;

    inner class SchedulingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemSchedulingBinding.bind(itemView);

        fun bindItem(scheduling: SchedulingDto, onItemSelected: (SchedulingDto) -> Unit) {
            binding.tvName.text = scheduling.patientName;
            binding.tvDate.text = scheduling.consultationDate;
            when (scheduling.state) {
                PATIENTE_MISSING -> {
                    binding.tvState.setTextColor(itemView.context.getColor(R.color.redText))
                    binding.tvState.setText(R.string.patientMissing)
                }
                PATIENTE_ATTENDED -> {
                    binding.tvState.setTextColor(itemView.context.getColor(R.color.greenText))
                    binding.tvState.setText(R.string.patientAttended)
                }
                else -> {
                    binding.tvState.setTextColor(itemView.context.getColor(R.color.orangeText))
                    binding.tvState.setText(R.string.patientScheduled)
                }
            }
            binding.root.setOnClickListener { onItemSelected(scheduling) }
        }

    }
}