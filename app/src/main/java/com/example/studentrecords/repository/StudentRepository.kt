package com.example.studentrecords.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.studentrecords.api.ApiInterface
import com.example.studentrecords.api.RetrofitHelper
import com.example.studentrecords.db.StudentDatabase
import com.example.studentrecords.helper.Helper
import com.example.studentrecords.model.StudentResponseModel
import com.example.studentrecords.model.StudentsDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentRepository(
    private val apiInterface: ApiInterface,
    private val studentDatabase: StudentDatabase,
    private val context: Context
) {

    var liveDataList = MutableLiveData<List<StudentsDataModel>?>()

    companion object {
        private const val TAG = "StudentRepository"
    }

    fun getStudentList() {
        if (Helper.checkInternetConnection(context)) {
            Log.d(TAG, "getStudentList: from api")
            fetchStudentListFromApi()
        } else {
            // check if data exists in DB and fetch it if available
            Log.d(TAG, "getStudentList: fromdb")
            var studentListFromDb = getStudentListFromDb()
            if (!studentListFromDb.isEmpty()) {
                Log.d(TAG, "getStudentList: db have data")
                liveDataList.postValue(studentListFromDb)
            }
        }
    }

    private fun getStudentListFromDb(): List<StudentsDataModel> {
        return studentDatabase.studentDao().getStudentData()
    }

    private fun fetchStudentListFromApi() {
        val retrofitHelper = RetrofitHelper.getInstance()
        val apiInterface = retrofitHelper.create(ApiInterface::class.java)
        val call = apiInterface.getStudentList()
        call.enqueue(object : Callback<StudentResponseModel> {
            override fun onResponse(
                call: Call<StudentResponseModel>,
                response: Response<StudentResponseModel>
            ) {
                liveDataList.postValue(response.body()!!.students)
                if (!response.body()!!.students.isNullOrEmpty()) {
                    addStudentListToDb(response.body()!!.students)
                }
            }

            override fun onFailure(call: Call<StudentResponseModel>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })
    }

    fun addStudentListToDb(students: List<StudentsDataModel>) {
        CoroutineScope(Dispatchers.Default).launch {
            studentDatabase.studentDao().addStudentData(students)
        }
    }
}