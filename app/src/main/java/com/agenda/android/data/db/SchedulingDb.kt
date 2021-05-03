package com.agenda.android.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agenda.android.data.dao.SchedulingDao
import com.agenda.android.data.entity.Scheduling


@Database(
    entities = [Scheduling::class],
    version = 2
)
abstract class SchedulingDb : RoomDatabase(){

    abstract fun schedulingDao(): SchedulingDao;

    companion object {
        private const val DATABASE_NAME = "db_scheduling";
        private lateinit var mInstance: SchedulingDb;

        val instance
            get() = mInstance

        fun initialize(context: Context) {
            mInstance = Room.databaseBuilder(context, SchedulingDb::class.java, DATABASE_NAME).build();
        }
    }



}
