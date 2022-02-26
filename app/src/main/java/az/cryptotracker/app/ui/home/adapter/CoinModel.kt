package az.cryptotracker.app.ui.home.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinModel(
    val coinId: String,
    val coinName: String,
    val coinPrice: String,
    val coinPriceRaw: Double
) : Parcelable
