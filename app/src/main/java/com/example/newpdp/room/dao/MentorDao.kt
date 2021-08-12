package com.example.newpdp.room.dao

import androidx.room.*
import com.example.newpdp.models.Mentor
@Dao
interface MentorDao {
    @Insert
    fun add(mentor: Mentor)

    @Delete
    fun delete(mentor: Mentor)

    @Update
    fun edit(mentor: Mentor)

    @Query("select * from mentor_table")
    fun getAll(): List<Mentor>

    @Query("select * from mentor_table where mentor_id=:id")
    fun getById(id: Int): Mentor
}