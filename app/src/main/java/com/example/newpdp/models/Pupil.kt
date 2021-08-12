package com.example.newpdp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pupil_table")
class Pupil {
    @PrimaryKey(autoGenerate = true)
    var pupil_id: Int = -1
    var pupil_name: String? = null
    var second_name: String? = null
    var third_name: String? = null
    var groupId: Int? = null
    var lesson_time: String? = null
    var lesson_date: String? = null
    var registraited_date: String? = null


    constructor(
        pupil_id: Int,
        pupil_name: String?,
        second_name: String?,
        third_name: String?,
        groupId: Int?,
        leddon_time: String?,
        leddon_date: String?,
        registraited_date: String?
    ) {
        this.pupil_id = pupil_id
        this.pupil_name = pupil_name
        this.second_name = second_name
        this.third_name = third_name
        this.groupId = groupId
        this.lesson_time = leddon_time
        this.lesson_date = leddon_date
        this.registraited_date = registraited_date
    }

    constructor(
        pupil_name: String?,
        second_name: String?,
        third_name: String?,
        groupId: Int?,
        leddon_time: String?,
        leddon_date: String?,
        registraited_date: String?
    ) {
        this.pupil_name = pupil_name
        this.second_name = second_name
        this.third_name = third_name
        this.groupId = groupId
        this.lesson_time = leddon_time
        this.lesson_date = leddon_date
        this.registraited_date = registraited_date
    }

    constructor()
}
