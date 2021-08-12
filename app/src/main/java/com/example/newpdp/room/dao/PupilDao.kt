package com.example.newpdp.room.dao

import androidx.room.*
import com.example.newpdp.models.Pupil
@Dao
interface PupilDao {
    @Insert
    fun add(pupil: Pupil)

    @Delete
    fun delete(pupil: Pupil)

    @Update
    fun edit(pupil: Pupil)

    @Query("select * from pupil_table")
    fun getAll(): List<Pupil>

    @Query("select * from pupil_table where pupil_id=:id")
    fun getById(id: Int): Pupil
}