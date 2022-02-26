package az.cryptotracker.app.data.api.repository

import android.util.Log
import az.cryptotracker.app.data.api.base.CoinGeckoService
import az.cryptotracker.app.data.api.models.ApiResult
import az.cryptotracker.app.data.api.models.CoinResponse
import az.cryptotracker.app.data.api.models.ErrorModel
import az.cryptotracker.app.ui.home.adapter.CoinModel
import az.cryptotracker.app.util.mapper.DataClassMapper
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CoinGeckoRepositoryImpl @Inject constructor(
    private val coinGeckoService: CoinGeckoService
) : CoinGeckoRepository {

    override suspend fun fetchCoinPrice(
        ids: String?,
        currencies: String?,
        viewModelCallBack: (ApiResult) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            coinGeckoService.fetchCoinPrice(ids, currencies)
                .enqueue(object : Callback<CoinResponse> {
                    override fun onResponse(
                        call: Call<CoinResponse>,
                        response: Response<CoinResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            viewModelCallBack(ApiResult.Success<CoinResponse?>(body))
                        } else {
                            viewModelCallBack(ApiResult.Fail(ErrorModel(response.code())))
                        }
                    }

                    override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                        viewModelCallBack(ApiResult.Fail(ErrorModel(t = t)))
                    }
                })
        }
    }
}