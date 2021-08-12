package com.example.newpdp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "mentor_table")
class Mentor {
    @PrimaryKey(autoGenerate = true)
    var mentor_id: Int=0
    var mentor_name: String? = null
    var second_name: String? = null
    var third_name: String? = null
    var courseId: Int? = null

    constructor(
        mentor_id: Int,
        mentor_name: String?,
        second_name: String?,
        third_name: String?,
        courseId: Int?
    ) {
        this.mentor_id = mentor_id
        this.mentor_name = mentor_name
        this.second_name = second_name
        this.third_name = third_name
        this.courseId = courseId
    }

    constructor(
        mentor_name: String?,
        second_name: String?,
        third_name: String?,
        courseId: Int?
    ) {
        this.mentor_name = mentor_name
        this.second_name = second_name
        this.third_name = third_name
        this.courseId = courseId
    }

    constructor()
}
