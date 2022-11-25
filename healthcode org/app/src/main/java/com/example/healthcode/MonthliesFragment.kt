package com.example.healthcode

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.healthcode.databinding.FragmentMonthliesBinding
import java.util.*


class MonthliesFragment : Fragment() {

    lateinit var binding:FragmentMonthliesBinding
    var dateFinal:String=" "
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMonthliesBinding.inflate(inflater,container,false)
        binding.startDate.setOnClickListener {
            Toast.makeText(activity,"Here",Toast.LENGTH_SHORT).show()
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = activity?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { view, year, monthOfYear, dayOfMonth ->
                        binding.startDate.setText((dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year))

                    },
                    year,
                    month,
                    day
                )
            }
            datePickerDialog?.show()
            println("Hello $day")
            var m2 = 0; var d2:Int = 0;

            // Return if year is leap year or not.
            fun isLeap(y: Int): Boolean {
                return if (y % 100 != 0 && y % 4 == 0 || y % 400 == 0) true else false
            }

            // Given a date, returns number of days elapsed
            // from the beginning of the current year (1st
            // jan).
            fun offsetDays(d: Int, m: Int, y: Int): Int {
                var offset = d
                if (m - 1 == 11) offset += 335
                if (m - 1 == 10) offset += 304
                if (m - 1 == 9) offset += 273
                if (m - 1 == 8) offset += 243
                if (m - 1 == 7) offset += 212
                if (m - 1 == 6) offset += 181
                if (m - 1 == 5) offset += 151
                if (m - 1 == 4) offset += 120
                if (m - 1 == 3) offset += 90
                if (m - 1 == 2) offset += 59
                if (m - 1 == 1) offset += 31
                if (isLeap(y) && m > 2) offset += 1
                return offset
            }

            // Given a year and days elapsed in it, finds
            // date by storing results in d and m.
            fun revoffsetDays(offset: Int, y: Int) {
                var offset = offset
                val month = intArrayOf(
                    0, 31, 28, 31, 30, 31, 30,
                    31, 31, 30, 31, 30, 31
                )
                if (isLeap(y)) month[2] = 29
                var i: Int
                i = 1
                while (i <= 12) {
                    if (offset <= month[i]) break
                    offset = offset - month[i]
                    i++
                }
                d2 = offset
                m2 = i
            }

            fun addDays(d1: Int, m1: Int, y1: Int, x: Int) {
                var x = x
                val offset1 = offsetDays(d1, m1, y1)
                val remDays = if (isLeap(y1)) 366 - offset1 else 365 - offset1

                var y2:Int=0;
                var offset2 = 0
                if (x <= remDays) {
                    y2 = y1
                    offset2 = offset1 + x
                } else {
                    // x may store thousands of days.
                    // We find correct year and offset
                    // in the year.
                    x -= remDays
                    y2 = y1 + 1
                    var y2days = if (isLeap(y2)) 366 else 365
                    while (x >= y2days) {
                        x -= y2days
                        y2++
                        y2days = if (isLeap(y2)) 366 else 365
                    }
                    offset2 = x
                }
                revoffsetDays(offset2, y2)
                println(
                    "d2 = " + d2 + ", m2 = " +
                            m2 + ", y2 = " + y2
                )
                dateFinal=d2.toString() +" - "+m2+" - "+y2
                println("This is final date $dateFinal")

            }
            addDays(day,month+1,year,28)
            binding.expectedDate.setText(dateFinal)
//            setDate=0;setMonth=0;setYear=0
        }




        return binding.root
    }
}