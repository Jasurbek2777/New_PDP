package com.example.newpdp.room.dao

import androidx.room.*
import com.example.newpdp.models.Group
@Dao
interface GroupDao {
    @Insert
    fun add(group: Group)

    @Delete
    fun delete(group: Group)

    @Update
    fun edit(group: Group)

    @Query("select * from group_table")
    fun getAll(): List<Group>

    @Query("select * from group_table where group_id=:id")
    fun getById(id: Int): Group
}