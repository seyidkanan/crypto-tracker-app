package az.cryptotracker.app.data.api.repository

import az.cryptotracker.app.data.api.models.ApiResult
import az.cryptotracker.app.data.api.models.CoinResponse
import retrofit2.Call

interface CoinGeckoRepository {

    suspend fun fetchCoinPrice(
        ids: String? = null,
        currencies: String? = null,
        viewModelCallBack: (ApiResult) -> Unit
    )

}