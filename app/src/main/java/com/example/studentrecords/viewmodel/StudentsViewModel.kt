package com.example.studentrecords.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentrecords.model.StudentResponseModel
import com.example.studentrecords.model.StudentsDataModel
import com.example.studentrecords.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsViewModel(private val studentRepository: StudentRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.getStudentList()
        }
    }

    val students: MutableLiveData<List<StudentsDataModel>?>
        get() = studentRepository.liveDataList
}