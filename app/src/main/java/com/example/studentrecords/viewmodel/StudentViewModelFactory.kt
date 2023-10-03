package com.example.studentrecords.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentrecords.repository.StudentRepository

class StudentViewModelFactory(private val studentRepository: StudentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentsViewModel(studentRepository) as T
    }
}