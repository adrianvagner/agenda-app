package com.agenda.android.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agenda.android.R
import com.agenda.android.databinding.ItemSchedulingBinding
import com.agenda.android.dto.scheduling.SchedulingDto

class ListSchedulingAdapter : RecyclerView.Adapter<ListSchedulingAdapter.SchedulingViewHolder>() {

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

        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size;

    inner class SchedulingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val binding = ItemSchedulingBinding.bind(itemView);

        fun bindItem(scheduling: SchedulingDto) {
            binding.tvName.text = scheduling.patientName;
            binding.tvDate.text = scheduling.consultationDate;
            binding.tvState.text = scheduling.state;
        }

    }
}