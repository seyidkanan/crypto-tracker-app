package az.cryptotracker.app.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import az.cryptotracker.app.R
import az.cryptotracker.app.data.api.models.ApiResult
import az.cryptotracker.app.data.api.models.CoinResponse
import az.cryptotracker.app.data.api.repository.CoinGeckoRepository
import az.cryptotracker.app.data.db.dao.CoinDao
import az.cryptotracker.app.data.db.model.CoinDbModel
import az.cryptotracker.app.data.db.model.CoinMinMaxModel
import az.cryptotracker.app.util.mapper.DataClassMapper
import az.cryptotracker.app.ui.home.adapter.CoinModel
import az.cryptotracker.app.util.TimeUtil
import az.cryptotracker.app.util.android.NotificationUtil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.random.Random

@HiltWorker
class CoinPriceWatcherWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted params: WorkerParameters,
    private val coinDao: CoinDao,
    private val coinRepository: CoinGeckoRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            //get min max price of coin
            val listOfMinMaxValue = coinDao.loadAllCoinMinMaxValue()
            if (listOfMinMaxValue.isNotEmpty()) {
                coinRepository.fetchCoinPrice(
                    "bitcoin,ethereum,ripple",
                    "usd"
                ) { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success<*> -> {
                            val body = apiResult.result as CoinResponse?
                            val coinList: ArrayList<CoinModel> =
                                DataClassMapper.coinResponseModelToCoinModel(body)

                            coinList.forEach { coinModel ->
                                listOfMinMaxValue.forEach { coinMinMaxModel ->
                                    if (coinModel.coinId == coinMinMaxModel.coinId) {
                                        //insert to db
                                        insertToDb(coinModel)
                                        //check range min max
                                        checkMinMaxPrice(coinModel, coinMinMaxModel)
                                    }
                                }
                            }
                        }
                        is ApiResult.Fail -> {
                            //show error
                        }
                    }
                }
            }
        }
        return Result.success()
    }

    private fun checkMinMaxPrice(coinModel: CoinModel, coinMinMaxModel: CoinMinMaxModel) {
        if (coinModel.coinPriceRaw < coinMinMaxModel.min ||
            coinModel.coinPriceRaw > coinMinMaxModel.max
        ) {
            NotificationUtil.createNotification(
                context = context,
                notificationId = Random.nextInt(0, 100),
                channelId = 72.toString(),
                titleText = context.getString(R.string.app_name),
                contentText = "${coinModel.coinName} teyin edilen min max meblegden kechib"
            )
        }
    }

    private fun insertToDb(coinModel: CoinModel) {
        Thread {
            coinDao.insertAllCoinData(
                CoinDbModel(
                    coinId = coinModel.coinId,
                    coinName = coinModel.coinName,
                    coinPrice = coinModel.coinPriceRaw,
                    date = TimeUtil.getCurrentUnixTime()
                )
            )
        }.start()
    }


}