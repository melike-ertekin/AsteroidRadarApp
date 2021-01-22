package com.example.asteroidradarapp.utils

import com.example.asteroidradarapp.utils.Constants.API_QUERY_DATE_FORMAT
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Global {

    fun getToday(): String = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)

    fun addDayToToday(day:Int): String = LocalDate.now().plusDays(7).format(DateTimeFormatter.ISO_LOCAL_DATE)

    val START_DATE = "2021-01-20"
    val END_DATE = "2021-01-27"
}