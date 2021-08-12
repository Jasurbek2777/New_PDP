package com.example.newpdp.room.dao

import androidx.room.*
import com.example.newpdp.models.Courses
@Dao
interface CourseDao {

    @Insert
    fun add(course: Courses)

    @Delete
    fun delete(course: Courses)

    @Update
    fun edit(course: Courses)

    @Query("select * from course_table")
    fun getAll(): List<Courses>

    @Query("select * from course_table where course_id=:id")
    fun getById(id: Int): Courses
}