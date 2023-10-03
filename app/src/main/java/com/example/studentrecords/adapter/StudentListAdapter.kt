package com.example.studentrecords.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studentrecords.Constants
import com.example.studentrecords.R
import com.example.studentrecords.model.StudentsDataModel
import com.example.studentrecords.view.DetailActivity

class StudentListAdapter() :
    RecyclerView.Adapter<StudentListAdapter.MyViewHolder>() {
    private var studentList: List<StudentsDataModel> = ArrayList()


    @SuppressLint("NotifyDataSetChanged")
    fun setStudentList(studentList: List<StudentsDataModel>?) {
        this.studentList = studentList?:ArrayList()
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.student_adapter_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        studentList?.get(position)?.let { holder.bind(it) }

        holder.itemView.setOnClickListener {

            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(Constants.STUDENT_DATA, studentList!![position])
            it.context.startActivity(intent)

        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var firstName: TextView = itemView.findViewById(R.id.firstName)
        var lastName: TextView = itemView.findViewById(R.id.lastName)
        var studentMajor: TextView = itemView.findViewById(R.id.studentMajor)
        var studentId: TextView = itemView.findViewById(R.id.id)

        fun bind(data: StudentsDataModel) {
            firstName.text = data.first_name ?: ""
            lastName.text = data.last_name ?: ""
            studentMajor.text = data.major ?: ""
            studentId.text = data.id?.toString() ?: ""
        }
    }


}


