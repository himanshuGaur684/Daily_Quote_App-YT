package gaur.himanshu.workmanageryt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gaur.himanshu.workmanageryt.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: Quote)

    @Query("SELECT * FROM Quote ORDER BY time DESC")
    fun getQuotesList(): Flow<List<Quote>>

}
