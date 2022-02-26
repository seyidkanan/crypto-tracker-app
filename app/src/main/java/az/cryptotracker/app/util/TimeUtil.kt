package az.cryptotracker.app.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun getCurrentUnixTime(): Long {
        return Date().time / 1000L
    }

    fun unixTimeToReadableFormat(time: Long): String {
        val sdf = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.ROOT
        )
        return sdf.format(Date(time * 1000))
    }

}