package com.example.studentrecords

import android.app.Application
import com.example.studentrecords.api.ApiInterface
import com.example.studentrecords.api.RetrofitHelper
import com.example.studentrecords.db.StudentDatabase
import com.example.studentrecords.repository.StudentRepository

class StudentApplication : Application() {

    lateinit var studentRepository: StudentRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val database = StudentDatabase.getDatabase(applicationContext)
        studentRepository = StudentRepository(apiInterface,database,applicationContext)
    }
}