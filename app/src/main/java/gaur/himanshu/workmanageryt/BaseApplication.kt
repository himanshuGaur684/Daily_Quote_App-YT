package gaur.himanshu.workmanageryt

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

const val CHANNEL = "channel"
const val NAME = "name"

@HiltAndroidApp
class BaseApplication : Application() {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        WorkManager.initialize(
            this, Configuration.Builder()
                .setWorkerFactory(hiltWorkerFactory).build()
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(CHANNEL, NAME, NotificationManager.IMPORTANCE_DEFAULT)

            val notificationManager =
                this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(
                notificationChannel
            )
        }
    }
}