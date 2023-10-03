package com.example.studentrecords.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.studentrecords.Constants
import com.example.studentrecords.databinding.ActivityDetailBinding
import com.example.studentrecords.model.StudentsDataModel
import com.example.studentrecords.viewmodel.DetailActivityViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailActivityViewModel: DetailActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailActivityViewModel = ViewModelProvider(this)[DetailActivityViewModel::class.java]

        val studentData =
            if (intent.getSerializableExtra(Constants.STUDENT_DATA) is StudentsDataModel) {
                intent.getSerializableExtra(Constants.STUDENT_DATA) as StudentsDataModel
            } else {
                null
            }

        if (studentData != null) {
            bindData(studentData)
        }
    }

    private fun bindData(studentData: StudentsDataModel) {
        binding.firstNameTv.text = studentData.first_name ?: ""
        binding.lastNameTv.text = studentData.last_name ?: ""
        binding.gpaTv.text = studentData.gpa.toString() ?: ""
        binding.idTv.text = studentData.id.toString() ?: ""
        binding.genderTv.text = studentData.gender ?: ""
        binding.ageTv.text = studentData.age.toString() ?: ""
        binding.majorTv.text = studentData.major.toString() ?: ""
    }
}