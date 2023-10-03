package com.example.studentrecords.api

import com.example.studentrecords.model.StudentResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("6a9d0c64-0446-4820-a44a-e2de48503539")
     fun getStudentList(): Call<StudentResponseModel>
}