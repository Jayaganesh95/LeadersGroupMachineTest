package com.example.studentrecords.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentrecords.model.StudentsDataModel

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStudentData(students: List<StudentsDataModel>)

    @Query("SELECT * FROM students")
    fun getStudentData(): List<StudentsDataModel>
}