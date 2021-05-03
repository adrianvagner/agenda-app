package com.agenda.android.app

import android.app.Application
import com.agenda.android.data.db.SchedulingDb

class SchedulingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SchedulingDb.initialize(applicationContext)
    }


}