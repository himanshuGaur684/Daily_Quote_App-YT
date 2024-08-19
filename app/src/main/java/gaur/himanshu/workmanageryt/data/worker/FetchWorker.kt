package gaur.himanshu.workmanageryt.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import gaur.himanshu.workmanageryt.data.local.QuoteDao
import gaur.himanshu.workmanageryt.data.mappers.toDomain
import gaur.himanshu.workmanageryt.data.remote.ApiService

const val ONE_TIME_WORK_REQUEST = "ONE_TIME_WORK_REQUEST"

@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val apiService: ApiService,
    private val quoteDao: QuoteDao
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            val response = apiService.getQuote().toDomain(ONE_TIME_WORK_REQUEST)
            quoteDao.insert(response)
            val data = Data.Builder()
                .putString(QUOTE, Gson().toJson(response)).build()
            Result.success(data)
        } catch (e: Exception) {
            Result.failure()
        }
    }
}