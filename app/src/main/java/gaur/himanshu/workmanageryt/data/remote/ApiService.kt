package gaur.himanshu.workmanageryt.data.remote

import gaur.himanshu.workmanageryt.data.model.QuoteDTO
import retrofit2.http.GET

interface ApiService {

    // https://dummyjson.com/quotes/random

    @GET("quotes/random")
    suspend fun getQuote(): QuoteDTO
}
