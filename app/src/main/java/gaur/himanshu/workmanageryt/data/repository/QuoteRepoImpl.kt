package gaur.himanshu.workmanageryt.data.repository

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import gaur.himanshu.workmanageryt.data.local.QuoteDao
import gaur.himanshu.workmanageryt.data.worker.FetchWorker
import gaur.himanshu.workmanageryt.data.worker.NotificationWorker
import gaur.himanshu.workmanageryt.data.worker.PeriodicWorker
import gaur.himanshu.workmanageryt.domain.model.Quote
import gaur.himanshu.workmanageryt.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class QuoteRepoImpl(
    private val workManager: WorkManager,
    private val quoteDao: QuoteDao
) : QuoteRepository {

    override fun getQuote() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constraints)
            .build()
        val notificationWOrkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .build()
        workManager.beginWith(workRequest)
            .then(notificationWOrkRequest)
            .enqueue()
    }

    override fun getAllQuotes(): Flow<List<Quote>> = quoteDao.getQuotesList()

    override fun setupPeriodicWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest =
            PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        workManager.enqueueUniquePeriodicWork(
            "laksdfjlasf",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}
