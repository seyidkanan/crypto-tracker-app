package az.cryptotracker.app.util.mapper

import az.cryptotracker.app.data.api.models.CoinResponse
import az.cryptotracker.app.ui.home.adapter.CoinModel

object DataClassMapper {

    fun coinResponseModelToCoinModel(coinResponse: CoinResponse?): ArrayList<CoinModel> {
        val coinList: ArrayList<CoinModel> = arrayListOf<CoinModel>()

        coinResponse?.bitcoin?.usd?.let {
            coinList.add(
                CoinModel(
                    coinId = "bitcoin",
                    coinName = "Bitcoin",
                    coinPrice = "$$it",
                    coinPriceRaw = it.toDouble()
                )
            )
        }
        coinResponse?.ethereum?.usd?.let {
            coinList.add(
                CoinModel(
                    coinId = "ethereum",
                    coinName = "Ethereum",
                    coinPrice = "$$it",
                    coinPriceRaw = it.toDouble()
                )
            )
        }
        coinResponse?.ripple?.usd?.let {
            coinList.add(
                CoinModel(
                    coinId = "ripple",
                    coinName = "Ripple",
                    coinPrice = "$$it",
                    coinPriceRaw = it.toDouble()
                )
            )
        }
        return coinList
    }

}