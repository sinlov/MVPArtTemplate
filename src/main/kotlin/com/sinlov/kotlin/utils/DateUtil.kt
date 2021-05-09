package com.sinlov.kotlin.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class DateUtil {
    companion object {
        fun nowDateStr(): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        }
    }
}