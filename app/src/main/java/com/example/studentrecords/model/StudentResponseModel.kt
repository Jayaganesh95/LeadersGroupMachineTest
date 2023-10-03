package com.example.studentrecords.model

data class StudentResponseModel(
    val status: String,
    val students: List<StudentsDataModel>
)