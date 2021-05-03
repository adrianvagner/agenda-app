package com.agenda.android.ui.scheduling.contract

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.agenda.android.dto.scheduling.SchedulingDto
import com.agenda.android.ui.scheduling.SchedulingActivity

class SchedulingContract : ActivityResultContract<SchedulingDto, Unit>() {


    override fun createIntent(context: Context, input: SchedulingDto?) =
        Intent(context, SchedulingActivity::class.java)
            .putExtra(EXTRA_SCHEDULING, input)

    override fun parseResult(resultCode: Int, intent: Intent?) = Unit

    companion object {
        const val EXTRA_SCHEDULING = "scheduling"
    }
}