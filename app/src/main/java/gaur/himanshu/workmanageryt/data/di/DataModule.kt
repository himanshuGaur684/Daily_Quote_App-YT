package gaur.himanshu.workmanageryt.data.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.workmanageryt.data.local.QuoteDao
import gaur.himanshu.workmanageryt.data.local.QuoteDatabase
import gaur.himanshu.workmanageryt.data.remote.ApiService
import gaur.himanshu.workmanageryt.data.repository.QuoteRepoImpl
import gaur.himanshu.workmanageryt.domain.repository.QuoteRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAPiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return QuoteDatabase.getInstance(context)
    }

    @Provides
    fun provideQuoteDao(quoteDatabase: QuoteDatabase): QuoteDao {
        return quoteDatabase.getQuoteDao()
    }

    @Provides
    @Singleton
    fun provideWorkmanager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideQuoteRepository(workManager: WorkManager, quoteDao: QuoteDao): QuoteRepository {
        return QuoteRepoImpl(workManager, quoteDao)
    }
}












