package com.demal.utill

import com.demal.model.DateIntegerFormat
import com.demal.model.data.entity.tours.LikableTour
import java.text.SimpleDateFormat
import java.util.*

class DateIdentifier {
     fun statusTour(tour: LikableTour): Boolean {
        val currentDate = getCurrentDate()
        val tourDate = getTourDate(tour)

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

    private fun getTourDate(tour: LikableTour): DateIntegerFormat {
        val dateStringBuilder = StringBuilder().append(tour.startDate)
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
}