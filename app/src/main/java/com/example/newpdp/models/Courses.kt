package com.example.newpdp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "course_table")

class Courses {

    @PrimaryKey(autoGenerate = true)
    var course_id: Int=0
    var coure_name: String? = null
    var course_desc: String? = null

    constructor()
    constructor(id: Int, name: String?, desc: String?) {
        this.course_id = id
        this.coure_name = name
        this.course_desc = desc
    }

    constructor(name: String?, desc: String?) {
        this.coure_name = name
        this.course_desc = desc
    }


}