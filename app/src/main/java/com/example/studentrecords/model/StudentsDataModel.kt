package com.example.studentrecords.model


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "students")
data class StudentsDataModel(
    @PrimaryKey(autoGenerate = true)
    val student_id: Int?,
    val id: Int?,
    val first_name: String?,
    val last_name: String?,
    val age: Int?,
    val gender: String?,
    val major: String?,
    val gpa: Double?,
):java.io.Serializable