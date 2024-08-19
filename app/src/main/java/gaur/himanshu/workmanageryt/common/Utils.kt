package gaur.himanshu.workmanageryt.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTimestampToDMY(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = Date(timestamp)
    return sdf.format(date)
}