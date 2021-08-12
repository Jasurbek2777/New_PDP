package com.example.newpdp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newpdp.models.Courses
import com.example.newpdp.models.Group
import com.example.newpdp.models.Mentor
import com.example.newpdp.models.Pupil
import com.example.newpdp.room.dao.CourseDao
import com.example.newpdp.room.dao.GroupDao
import com.example.newpdp.room.dao.MentorDao
import com.example.newpdp.room.dao.PupilDao

class AppDataBase {
    @Database(entities = [Courses::class, Mentor::class, Pupil::class, Group::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun groupDao():GroupDao
        abstract fun courseDao():CourseDao
        abstract fun pupilDao(): PupilDao
        abstract fun mentorDao(): MentorDao
        companion object {
            private var instance: AppDatabase? = null

            @Synchronized
            fun getInstance(context: Context): AppDatabase {
                if (instance == null)
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "AppDataBase"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                return instance!!
            }
        }
    }
}