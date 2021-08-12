package com.example.newpdp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
class Group {
    @PrimaryKey(autoGenerate = true)
    var group_id: Int =0
    var group_name: String? = null
    var group_status: Int? = null
    var group_course_id: Int? = null
    var group_mentor_id: Int? = null
    var group_lesson_time: String? = null
    var group_lesson_day: String? = null

    constructor(
        group_id: Int,
        group_name: String?,
        group_status: Int?,
        group_course_id: Int?,
        group_mentor_id: Int?,
        group_lesson_time: String?,
        group_lesson_day: String?
    ) {
        this.group_id = group_id
        this.group_name = group_name
        this.group_status = group_status
        this.group_course_id = group_course_id
        this.group_mentor_id = group_mentor_id
        this.group_lesson_time = group_lesson_time
        this.group_lesson_day = group_lesson_day
    }

    constructor(
        group_name: String?,
        group_status: Int?,
        group_course_id: Int?,
        group_mentor_id: Int?,
        group_lesson_time: String?,
        group_lesson_day: String?
    ) {
        this.group_name = group_name
        this.group_status = group_status
        this.group_course_id = group_course_id
        this.group_mentor_id = group_mentor_id
        this.group_lesson_time = group_lesson_time
        this.group_lesson_day = group_lesson_day
    }

    constructor()
}