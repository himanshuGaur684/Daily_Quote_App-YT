package gaur.himanshu.workmanageryt.domain.repository

import gaur.himanshu.workmanageryt.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuote()

    fun getAllQuotes(): Flow<List<Quote>>

    fun setupPeriodicWorkRequest()


}