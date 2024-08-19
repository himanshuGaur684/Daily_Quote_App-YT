package gaur.himanshu.workmanageryt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gaur.himanshu.workmanageryt.domain.model.Quote

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, QuoteDatabase::class.java, "quote_db")
                .build()
    }

    abstract fun getQuoteDao(): QuoteDao

}