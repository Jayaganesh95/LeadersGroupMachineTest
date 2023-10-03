package com.example.studentrecords.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentrecords.StudentApplication
import com.example.studentrecords.adapter.StudentListAdapter
import com.example.studentrecords.databinding.ActivityMainBinding
import com.example.studentrecords.model.StudentsDataModel
import com.example.studentrecords.viewmodel.StudentViewModelFactory
import com.example.studentrecords.viewmodel.StudentsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var studentsViewModel: StudentsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: StudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as StudentApplication).studentRepository
        initRecyclerView()
        initSearchView()
        studentsViewModel = ViewModelProvider(
            this,
            StudentViewModelFactory(repository)
        )[StudentsViewModel::class.java]

        studentsViewModel.students.observe(this, Observer {
            recyclerAdapter.setStudentList(it)
        })
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query!!.isNotEmpty() && query.length > 2) {
                    filterList(query)
                } else if (query.isEmpty()) {
                    filterList(query)
                }

                return true
            }
        })
    }

    private fun filterList(query: String?) {
        val filteredList = ArrayList<StudentsDataModel>()
        if (!query.isNullOrEmpty()) { // Check if query is not null or empty
            for (student in studentsViewModel.students.value.orEmpty()) {
                val fullName = "${student.first_name} ${student.last_name}".lowercase()
                val major = student.major ?: ""

                if (fullName.contains(query.lowercase()) ||
                    major.contains(query)
                ) {
                    filteredList.add(student)
                }
            }
        } else {
            // If the query is null or empty, show all students
            filteredList.addAll(studentsViewModel.students.value.orEmpty())
        }
        recyclerAdapter.setStudentList(filteredList)
    }


    private fun initRecyclerView() {
        recyclerAdapter = StudentListAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerAdapter
        }
    }
}
