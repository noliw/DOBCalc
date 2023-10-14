package com.example.dobcalc

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var dateTv : TextView
    private lateinit var minutesTv: TextView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dateTv = findViewById(R.id.textView2)
        minutesTv = findViewById(R.id.textView4)

        val btnDatePicker: Button = findViewById(R.id.button)

        btnDatePicker.setOnClickListener {

            clickDatePicker()
        }
    }


    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                val sDate = "$dayOfMonth/${month+1}/$year"
                dateTv.text = sDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(sDate)
                val dateInMin = theDate.time / 60000
                val currDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currDateInMinutes = currDate.time / 60000
                val difference = (currDateInMinutes - dateInMin).toString()

                minutesTv.text = difference

            },
                year,
                month,
                day
        ).show()

    }
}