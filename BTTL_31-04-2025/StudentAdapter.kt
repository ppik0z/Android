package com.example.bttl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val studentList: MutableList<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    // Tạo ViewHolder cho RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return StudentViewHolder(view)
    }

    // Liên kết dữ liệu với ViewHolder
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.nameTextView.text = student.name
        holder.mssvTextView.text = student.mssv
    }

    // Trả về số lượng phần tử trong danh sách
    override fun getItemCount(): Int {
        return studentList.size
    }

    // Thêm sinh viên mới vào danh sách
    fun addStudent(student: Student) {
        studentList.add(student)
        notifyDataSetChanged()  // Cập nhật RecyclerView
    }

    // ViewHolder để giữ các phần tử trong mỗi item của RecyclerView
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(android.R.id.text1)
        val mssvTextView: TextView = itemView.findViewById(android.R.id.text2)
    }
}
