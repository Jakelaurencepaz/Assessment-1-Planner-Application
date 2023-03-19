package com.example.planneassessment1application2

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.icu.text.DateFormat.HourCycle
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mainscreen.*
import java.time.Month
import java.time.Year
import java.util.Calendar

class mainscreen : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)

        val bottomSheetFragment = BottomSheetFragment()

        btn_show.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager,"BottomSheetDialog")
        }

        pickDate()
    }

    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

    }

    private fun pickDate(){

        btn_timePicker.setOnClickListener {

            getDateTimeCalendar()
            DatePickerDialog(this,this,year, month, day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        tv_textTime.text = "$savedDay-$savedMonth-$savedYear\n Hours: $savedHour Minutes: $savedMinute"
    }
}