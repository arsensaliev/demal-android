package com.demal.utils.date

import java.text.SimpleDateFormat
import java.util.*

class DateIdentifier {
    fun statusTour(startDateTour: String?): Boolean {
        val currentDate = getCurrentDate()
        val tourDate = getTourDate(startDateTour)
        return (currentDate.year < tourDate.year
                || currentDate.year <= tourDate.year
                && currentDate.month < tourDate.month
                || currentDate.year <= tourDate.year
                && currentDate.month <= tourDate.month
                && currentDate.day < tourDate.day
                || currentDate.year <= tourDate.year
                && currentDate.month <= tourDate.month
                && currentDate.day <= tourDate.day
                && currentDate.hour < tourDate.hour
                || currentDate.year <= tourDate.year
                && currentDate.month <= tourDate.month
                && currentDate.day <= tourDate.day
                && currentDate.hour <= tourDate.hour
                && currentDate.minutes < tourDate.minutes
                || currentDate.year <= tourDate.year
                && currentDate.month <= tourDate.month
                && currentDate.day <= tourDate.day
                && currentDate.hour <= tourDate.hour
                && currentDate.minutes <= tourDate.minutes
                || currentDate.year <= tourDate.year
                && currentDate.month <= tourDate.month
                && currentDate.day <= tourDate.day
                && currentDate.hour <= tourDate.hour
                && currentDate.minutes <= tourDate.minutes
                && currentDate.seconds <= tourDate.seconds)
    }

    private fun getCurrentDate(): DateIntegerFormat {
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale("ru"))
        val currentDate = sdf.format(Date()).toString().split(" ")
        val dateStringList = currentDate[0].split("-")
        val timeStringList = currentDate[1].split(":")
        return DateIntegerFormat(
            year = dateStringList[0].toInt(),
            month = dateStringList[1].toInt(),
            day = dateStringList[2].toInt(),
            hour = timeStringList[0].toInt(),
            minutes = timeStringList[1].toInt(),
            seconds = timeStringList[2].toInt()
        )
    }

    fun getTourDate(startDateTour: String?): DateIntegerFormat {
        val dateStringBuilder = StringBuilder().append(startDateTour)
        dateStringBuilder.delete(dateStringBuilder.length - 5, dateStringBuilder.length)
        val startDateTour = dateStringBuilder.split("T")
        val dateTour = startDateTour[0].split("-")
        val timeTour = startDateTour[1].split(":")
        return DateIntegerFormat(
            year = dateTour[0].toInt(),
            month = dateTour[1].toInt(),
            day = dateTour[2].toInt(),
            hour = timeTour[0].toInt(),
            minutes = timeTour[1].toInt(),
            seconds = timeTour[2].toInt()
        )
    }

    val monthsMap = mapOf(
        1 to "Янаря",
        2 to "Февраля",
        3 to "Марта",
        4 to "Апреля",
        5 to "Мая",
        6 to "Июня",
        7 to "Июля",
        8 to "Августа",
        9 to "Сентября",
        10 to "Октября",
        11 to "Ноября",
        12 to "Декабря",
    )
}