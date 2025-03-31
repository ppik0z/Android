package com.example.bttl

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextMSSV: EditText
    private lateinit var buttonAddStudent: Button
    private lateinit var recyclerViewStudents: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editTextName = findViewById(R.id.editTextName)
        editTextMSSV = findViewById(R.id.editTextMSSV)
        buttonAddStudent = findViewById(R.id.buttonAddStudent)
        recyclerViewStudents = findViewById(R.id.recyclerViewStudents)


        studentAdapter = StudentAdapter(studentList)
        recyclerViewStudents.layoutManager = LinearLayoutManager(this)
        recyclerViewStudents.adapter = studentAdapter


        buttonAddStudent.setOnClickListener {
            val name = editTextName.text.toString()
            val mssv = editTextMSSV.text.toString()


            if (name.isEmpty() || mssv.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            } else {

                val student = Student(name, mssv)
                studentAdapter.addStudent(student)


                editTextName.text.clear()
                editTextMSSV.text.clear()
            }
        }
    }
}
