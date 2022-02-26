package az.cryptotracker.app.data.api.base

import az.cryptotracker.app.data.api.models.CoinResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoService {

    //https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,ripple&vs_currencies=usd
    @GET("simple/price")
    fun fetchCoinPrice(
        @Query("ids") ids: String?,
        @Query("vs_currencies") currencies: String?
    ): Call<CoinResponse>

}